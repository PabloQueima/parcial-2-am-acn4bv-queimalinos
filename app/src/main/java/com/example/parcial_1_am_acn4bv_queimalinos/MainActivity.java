package com.example.parcial_1_am_acn4bv_queimalinos;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.parcial_1_am_acn4bv_queimalinos.models.Ejercicio;
import com.example.parcial_1_am_acn4bv_queimalinos.models.Sesion;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private final Set<String> ejerciciosCompletados = new HashSet<>();
    private final Map<String, TextView> progresosPorSesion = new HashMap<>();
    private final Map<String, List<Ejercicio>> ejerciciosPorSesion = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout contenedorSesiones = findViewById(R.id.contenedorSesiones);
        List<Sesion> sesiones = generarSesionesMock();

        for (Sesion sesion : sesiones) {
            ejerciciosPorSesion.put(sesion.getTitulo(), sesion.getEjercicios());
            contenedorSesiones.addView(crearVistaSesion(sesion));
        }
    }

    // -------------------------------------------------
    // Datos mock
    // -------------------------------------------------
    private List<Sesion> generarSesionesMock() {
        List<Sesion> sesiones = new ArrayList<>();

        List<Ejercicio> piernas = Arrays.asList(
                new Ejercicio(getString(R.string.ejercicio_sentadillas), 3, 12, getString(R.string.desc_sentadillas), android.R.drawable.ic_menu_gallery),
                new Ejercicio(getString(R.string.ejercicio_zancadas), 3, 10, getString(R.string.desc_zancadas), android.R.drawable.ic_menu_gallery),
                new Ejercicio(getString(R.string.ejercicio_peso_muerto), 4, 8, getString(R.string.desc_peso_muerto), android.R.drawable.ic_menu_gallery)
        );
        sesiones.add(new Sesion(getString(R.string.sesion_piernas), piernas));

        List<Ejercicio> brazos = Arrays.asList(
                new Ejercicio(getString(R.string.ejercicio_flexiones), 3, 15, getString(R.string.desc_flexiones), android.R.drawable.ic_menu_gallery),
                new Ejercicio(getString(R.string.ejercicio_curl_biceps), 4, 10, getString(R.string.desc_biceps), android.R.drawable.ic_menu_gallery),
                new Ejercicio(getString(R.string.ejercicio_triceps_fondo), 3, 12, getString(R.string.desc_triceps), android.R.drawable.ic_menu_gallery)
        );
        sesiones.add(new Sesion(getString(R.string.sesion_brazos), brazos));

        List<Ejercicio> core = Arrays.asList(
                new Ejercicio(getString(R.string.ejercicio_plancha), 4, 30, getString(R.string.desc_plancha), android.R.drawable.ic_menu_gallery),
                new Ejercicio(getString(R.string.ejercicio_abdominales), 3, 20, getString(R.string.desc_abdominales), android.R.drawable.ic_menu_gallery),
                new Ejercicio(getString(R.string.ejercicio_mountain_climbers), 4, 20, getString(R.string.desc_mountain), android.R.drawable.ic_menu_gallery)
        );
        sesiones.add(new Sesion(getString(R.string.sesion_core), core));

        return sesiones;
    }

    // -------------------------------------------------
    // Crear vista de sesión
    // -------------------------------------------------
    private View crearVistaSesion(Sesion sesion) {
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

        TextView titulo = new TextView(this);
        titulo.setText(sesion.getTitulo());
        titulo.setTextSize(18f);
        titulo.setTextColor(ContextCompat.getColor(this, R.color.color_mid_dark));
        sesionLayout.addView(titulo);

        // 🔹 Progreso dinámico
        TextView progreso = new TextView(this);
        progreso.setText("0 de " + sesion.getEjercicios().size() + " ejercicios completados");
        progreso.setTextColor(ContextCompat.getColor(this, R.color.color_secondary));
        progreso.setPadding(0, 8, 0, 8);
        sesionLayout.addView(progreso);
        progresosPorSesion.put(sesion.getTitulo(), progreso);

        // 🔹 Botón desplegable
        Button boton = new Button(this);
        boton.setText(getString(R.string.boton_ver_ejercicios));
        boton.setBackgroundColor(ContextCompat.getColor(this, R.color.color_primary));
        boton.setTextColor(ContextCompat.getColor(this, R.color.color_light));
        sesionLayout.addView(boton);

        // 🔹 Contenedor ejercicios
        LinearLayout lista = new LinearLayout(this);
        lista.setOrientation(LinearLayout.VERTICAL);
        lista.setVisibility(View.GONE);
        lista.setPadding(16, 16, 16, 16);

        for (Ejercicio e : sesion.getEjercicios()) {
            lista.addView(crearVistaEjercicio(sesion.getTitulo(), e));
        }

        sesionLayout.addView(lista);

        boton.setOnClickListener(v -> {
            boolean visible = lista.getVisibility() == View.VISIBLE;
            lista.setVisibility(visible ? View.GONE : View.VISIBLE);
            boton.setText(visible ? getString(R.string.boton_ver_ejercicios) : getString(R.string.boton_ocultar_ejercicios));
        });

        return sesionLayout;
    }

    // -------------------------------------------------
    // Crear vista de ejercicio con CheckBox
    // -------------------------------------------------
    private View crearVistaEjercicio(String sesionTitulo, Ejercicio e) {
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

        TextView nombre = new TextView(this);
        nombre.setText(e.getNombre());
        nombre.setTextColor(ContextCompat.getColor(this, R.color.color_dark));
        nombre.setTextSize(16f);

        TextView detalle = new TextView(this);
        detalle.setText(e.getSeries() + " series x " + e.getRepeticiones() + " repeticiones\n" + e.getDescripcion());
        detalle.setTextColor(ContextCompat.getColor(this, R.color.color_mid_dark));
        detalle.setTextSize(14f);

        textoContainer.addView(nombre);
        textoContainer.addView(detalle);
        tarjeta.addView(textoContainer);

        CheckBox check = new CheckBox(this);
        tarjeta.addView(check);

        actualizarEstiloEjercicio(tarjeta, nombre, e.getNombre());

        check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) ejerciciosCompletados.add(e.getNombre());
            else ejerciciosCompletados.remove(e.getNombre());
            actualizarEstiloEjercicio(tarjeta, nombre, e.getNombre());
            actualizarProgresoSesion(sesionTitulo);
        });

        return tarjeta;
    }

    // -------------------------------------------------
    // Cambiar estilo visual del ejercicio
    // -------------------------------------------------
    private void actualizarEstiloEjercicio(View tarjeta, TextView nombre, String ejercicio) {
        boolean done = ejerciciosCompletados.contains(ejercicio);
        if (done) {
            tarjeta.setBackgroundColor(ContextCompat.getColor(this, R.color.color_success));
            nombre.setPaintFlags(nombre.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            tarjeta.setBackgroundColor(0xFFE9E9F3);
            nombre.setPaintFlags(nombre.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }

    // -------------------------------------------------
    // Actualizar progreso dinámico por sesión
    // -------------------------------------------------
    private void actualizarProgresoSesion(String sesionTitulo) {
        TextView progreso = progresosPorSesion.get(sesionTitulo);
        List<Ejercicio> lista = ejerciciosPorSesion.get(sesionTitulo);
        if (progreso == null || lista == null) return;

        long completados = lista.stream().filter(e -> ejerciciosCompletados.contains(e.getNombre())).count();
        long total = lista.size();

        progreso.setText(completados + " de " + total + " ejercicios completados" + (completados == total ? " ✅" : ""));
        progreso.setTextColor(ContextCompat.getColor(this,
                completados == total ? R.color.color_success : R.color.color_secondary));
    }
}
