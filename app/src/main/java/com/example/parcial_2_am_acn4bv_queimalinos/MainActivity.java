package com.example.parcial_2_am_acn4bv_queimalinos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private final Set<String> ejerciciosCompletados = new HashSet<>();
    private final Map<String, TextView> progresosPorSesion = new HashMap<>();
    private final Map<String, Integer> totalEjPorSesion = new HashMap<>();

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private LinearLayout contenedorSesiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        String uid = auth.getCurrentUser().getUid();
        String email = auth.getCurrentUser().getEmail();

        TextView bienvenida = findViewById(R.id.txtBienvenida);
        bienvenida.setText("Bienvenido " + email);

        Button logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            SharedPreferences.Editor editor = getSharedPreferences("prefs", MODE_PRIVATE).edit();
            editor.remove("usuarioEmail");
            editor.apply();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        contenedorSesiones = findViewById(R.id.contenedorSesiones);

        db.collection("usuarios").document(uid).get()
                .addOnSuccessListener(doc -> {
                    if (!doc.exists()) {
                        Toast.makeText(this, "Usuario sin datos en Firestore", Toast.LENGTH_LONG).show();
                        return;
                    }

                    List<String> sesionesAsignadas = (List<String>) doc.get("sesionesAsignadas");
                    if (sesionesAsignadas == null || sesionesAsignadas.isEmpty()) {
                        Toast.makeText(this, "No tenés sesiones asignadas", Toast.LENGTH_LONG).show();
                        return;
                    }

                    for (String sesionId : sesionesAsignadas) {
                        cargarSesionYEjercicios(sesionId);
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error leyendo usuario: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
    }

    private void cargarSesionYEjercicios(String sesionId) {
        db.collection("sesiones").document(sesionId).get()
                .addOnSuccessListener(snap -> {
                    if (!snap.exists()) return;

                    String titulo = snap.getString("titulo");
                    List<Map<String, Object>> ejerciciosData = (List<Map<String, Object>>) snap.get("ejercicios");
                    if (ejerciciosData == null || ejerciciosData.isEmpty()) {
                        crearVistaSesionConLista(titulo, Collections.emptyList());
                        return;
                    }

                    List<Object> ids = new ArrayList<>();
                    for (Map<String, Object> e : ejerciciosData) {
                        Object idObj = e.get("id");
                        if (idObj instanceof Long) ids.add(((Long) idObj).intValue());
                        else ids.add(idObj);
                    }

                    if (ids.size() == 0) {
                        crearVistaSesionConLista(titulo, Collections.emptyList());
                        return;
                    }

                    if (ids.size() <= 10) {
                        db.collection("ejercicios").whereIn("id", ids).get()
                                .addOnSuccessListener(query -> {
                                    Map<Integer, Map<String, Object>> ejerciciosMap = new HashMap<>();
                                    for (DocumentSnapshot d : query.getDocuments()) {
                                        Long idLong = d.getLong("id");
                                        if (idLong == null) continue;
                                        int idVal = idLong.intValue();
                                        Map<String, Object> campos = d.getData();
                                        campos.put("_docId", d.getId());
                                        ejerciciosMap.put(idVal, campos);
                                    }
                                    List<View> vistas = buildViewsFromSesionData(titulo, ejerciciosData, ejerciciosMap);
                                    agregarVistasSesion(titulo, vistas);
                                })
                                .addOnFailureListener(err ->
                                        Toast.makeText(this, "Error cargando ejercicios: " + err.getMessage(), Toast.LENGTH_LONG).show()
                                );
                    } else {
                        List<Task<DocumentSnapshot>> tasks = new ArrayList<>();
                        for (Object idObj : ids) {
                            int idVal = (idObj instanceof Integer) ? (Integer) idObj : Integer.parseInt(String.valueOf(idObj));
                            Task<DocumentSnapshot> t = db.collection("ejercicios")
                                    .whereEqualTo("id", idVal).limit(1).get()
                                    .continueWith(task -> {
                                        QuerySnapshot qs = task.getResult();
                                        if (qs != null && !qs.isEmpty()) return qs.getDocuments().get(0);
                                        return null;
                                    });
                            tasks.add(t);
                        }
                        Tasks.whenAllSuccess(tasks)
                                .addOnSuccessListener(results -> {
                                    Map<Integer, Map<String, Object>> ejerciciosMap = new HashMap<>();
                                    for (Object o : results) {
                                        if (!(o instanceof DocumentSnapshot)) continue;
                                        DocumentSnapshot d = (DocumentSnapshot) o;
                                        if (d == null || !d.exists()) continue;
                                        Long idLong = d.getLong("id");
                                        if (idLong == null) continue;
                                        int idVal = idLong.intValue();
                                        Map<String, Object> campos = d.getData();
                                        campos.put("_docId", d.getId());
                                        ejerciciosMap.put(idVal, campos);
                                    }
                                    List<View> vistas = buildViewsFromSesionData(titulo, ejerciciosData, ejerciciosMap);
                                    agregarVistasSesion(titulo, vistas);
                                })
                                .addOnFailureListener(err ->
                                        Toast.makeText(this, "Error cargando ejercicios individualmente: " + err.getMessage(), Toast.LENGTH_LONG).show()
                                );
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error cargando sesión: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
    }

    private List<View> buildViewsFromSesionData(String titulo, List<Map<String, Object>> ejerciciosData,
                                               Map<Integer, Map<String, Object>> ejerciciosMap) {
        List<View> vistas = new ArrayList<>();
        for (Map<String, Object> entry : ejerciciosData) {
            Number idNum = (Number) entry.get("id");
            int id = idNum != null ? idNum.intValue() : -1;
            Number seriesNum = (Number) entry.get("series");
            Number repsNum = (Number) entry.get("reps");
            int series = seriesNum != null ? seriesNum.intValue() : 0;
            int reps = repsNum != null ? repsNum.intValue() : 0;

            Map<String, Object> ejDoc = ejerciciosMap.get(id);
            if (ejDoc == null) {
                TextView missing = new TextView(this);
                missing.setText("Ejercicio id " + id + " no encontrado");
                vistas.add(missing);
                continue;
            }

            String nombre = ejDoc.get("nombre") != null ? ejDoc.get("nombre").toString() : "Sin nombre";
            String descripcion = ejDoc.get("descripcion") != null ? ejDoc.get("descripcion").toString() : "";
            String imageUrl = ejDoc.get("imageUrl") != null ? ejDoc.get("imageUrl").toString() : null;

            View tarjeta = crearTarjetaEjercicio(titulo, nombre, descripcion, imageUrl, series, reps);
            vistas.add(tarjeta);
        }
        return vistas;
    }

    private void agregarVistasSesion(String titulo, List<View> vistas) {
        runOnUiThread(() -> {
            LinearLayout sesionLayout = new LinearLayout(this);
            sesionLayout.setOrientation(LinearLayout.VERTICAL);
            sesionLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.color_light));
            sesionLayout.setPadding(24, 24, 24, 24);
            sesionLayout.setElevation(6);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 32);
            sesionLayout.setLayoutParams(params);

            TextView tituloView = new TextView(this);
            tituloView.setText(titulo);
            tituloView.setTextSize(18f);
            tituloView.setTextColor(ContextCompat.getColor(this, R.color.color_mid_dark));
            sesionLayout.addView(tituloView);

            TextView progreso = new TextView(this);
            progreso.setText("0 de " + vistas.size() + " ejercicios completados");
            progreso.setTextColor(ContextCompat.getColor(this, R.color.color_secondary));
            progreso.setPadding(0, 8, 0, 8);
            sesionLayout.addView(progreso);
            progresosPorSesion.put(titulo, progreso);
            totalEjPorSesion.put(titulo, vistas.size());

            Button boton = new Button(this);
            boton.setText(getString(R.string.boton_ver_ejercicios));
            boton.setBackgroundColor(ContextCompat.getColor(this, R.color.color_primary));
            boton.setTextColor(ContextCompat.getColor(this, R.color.color_light));
            sesionLayout.addView(boton);

            LinearLayout lista = new LinearLayout(this);
            lista.setOrientation(LinearLayout.VERTICAL);
            lista.setVisibility(View.GONE);
            lista.setPadding(16, 16, 16, 16);

            for (View v : vistas) lista.addView(v);

            sesionLayout.addView(lista);

            boton.setOnClickListener(v -> {
                boolean visible = lista.getVisibility() == View.VISIBLE;
                lista.setVisibility(visible ? View.GONE : View.VISIBLE);
                boton.setText(visible ? getString(R.string.boton_ver_ejercicios)
                        : getString(R.string.boton_ocultar_ejercicios));
            });

            contenedorSesiones.addView(sesionLayout);
        });
    }

    private View crearTarjetaEjercicio(String sesionTitulo, String nombre, String descripcion,
                                       String imageUrl, int series, int reps) {
        LinearLayout tarjeta = new LinearLayout(this);
        tarjeta.setOrientation(LinearLayout.HORIZONTAL);
        tarjeta.setPadding(16, 16, 16, 16);
        tarjeta.setBackgroundColor(0xFFE9E9F3);
        tarjeta.setElevation(4);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 8, 0, 8);
        tarjeta.setLayoutParams(params);

        LinearLayout textoContainer = new LinearLayout(this);
        textoContainer.setOrientation(LinearLayout.VERTICAL);
        textoContainer.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

        TextView nombreView = new TextView(this);
        nombreView.setText(nombre);
        nombreView.setTextColor(ContextCompat.getColor(this, R.color.color_dark));
        nombreView.setTextSize(16f);

        TextView detalle = new TextView(this);
        detalle.setText(series + " series x " + reps + " repeticiones\n" + descripcion);
        detalle.setTextColor(ContextCompat.getColor(this, R.color.color_mid_dark));
        detalle.setTextSize(14f);

        textoContainer.addView(nombreView);
        textoContainer.addView(detalle);
        tarjeta.addView(textoContainer);

        CheckBox check = new CheckBox(this);
        tarjeta.addView(check);

        check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) ejerciciosCompletados.add(sesionTitulo + "|" + nombre);
            else ejerciciosCompletados.remove(sesionTitulo + "|" + nombre);
            actualizarEstiloEjercicio(tarjeta, nombreView, sesionTitulo, nombre);
            actualizarProgresoSesion(sesionTitulo);
        });

        tarjeta.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DetalleEjercicioActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("descripcion", descripcion);
            intent.putExtra("series", series);
            intent.putExtra("repeticiones", reps);
            if (imageUrl != null) intent.putExtra("imageUrl", imageUrl);
            startActivity(intent);
        });

        return tarjeta;
    }

    private void actualizarEstiloEjercicio(LinearLayout tarjeta, TextView nombre, String sesionTitulo, String ejercicioNombre) {
        boolean hecho = ejerciciosCompletados.contains(sesionTitulo + "|" + ejercicioNombre);
        nombre.setPaintFlags(hecho ? nombre.getPaintFlags() | android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                : nombre.getPaintFlags() & (~android.graphics.Paint.STRIKE_THRU_TEXT_FLAG));
        tarjeta.setAlpha(hecho ? 0.5f : 1f);
    }

    private void actualizarProgresoSesion(String sesionTitulo) {
        TextView progreso = progresosPorSesion.get(sesionTitulo);
        Integer total = totalEjPorSesion.get(sesionTitulo);
        if (progreso == null || total == null) return;
        long hechos = ejerciciosCompletados.stream().filter(k -> k.startsWith(sesionTitulo + "|")).count();
        progreso.setText(hechos + " de " + total + " ejercicios completados");
    }
}
