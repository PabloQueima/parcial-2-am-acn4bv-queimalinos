package com.example.parcial_1_am_acn4bv_queimalinos;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.parcial_1_am_acn4bv_queimalinos.models.Ejercicio;
import com.example.parcial_1_am_acn4bv_queimalinos.models.Sesion;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout contenedorSesiones = findViewById(R.id.contenedorSesiones);

        // 🔹 Datos mock
        List<Sesion> sesiones = generarSesionesMock();

        // 🔹 Render dinámico
        for (Sesion sesion : sesiones) {
            contenedorSesiones.addView(crearVistaSesion(sesion));
        }
    }

    // -------------------------------------------------
    // 🔸 Métodos auxiliares
    // -------------------------------------------------

    private List<Sesion> generarSesionesMock() {
        List<Sesion> sesiones = new ArrayList<>();

        // Sesión 1: Piernas
        List<Ejercicio> ejerciciosPiernas = new ArrayList<>();
        ejerciciosPiernas.add(new Ejercicio("Sentadillas", 3, 12, "Ejercicio básico para trabajar cuádriceps y glúteos.", android.R.drawable.ic_menu_gallery));
        ejerciciosPiernas.add(new Ejercicio("Zancadas", 3, 10, "Fortalece piernas y equilibrio.", android.R.drawable.ic_menu_gallery));
        ejerciciosPiernas.add(new Ejercicio("Peso muerto", 4, 8, "Trabaja femorales y espalda baja.", android.R.drawable.ic_menu_gallery));

        sesiones.add(new Sesion("Sesión 1: Piernas", ejerciciosPiernas));

        // Sesión 2: Brazos
        List<Ejercicio> ejerciciosBrazos = new ArrayList<>();
        ejerciciosBrazos.add(new Ejercicio("Flexiones", 3, 15, "Trabaja pectorales y tríceps.", android.R.drawable.ic_menu_gallery));
        ejerciciosBrazos.add(new Ejercicio("Curl de bíceps", 4, 10, "Fortalece los bíceps.", android.R.drawable.ic_menu_gallery));
        ejerciciosBrazos.add(new Ejercicio("Tríceps fondo", 3, 12, "Ejercicio para tríceps.", android.R.drawable.ic_menu_gallery));

        sesiones.add(new Sesion("Sesión 2: Brazos", ejerciciosBrazos));

        // Sesión 3: Core
        List<Ejercicio> ejerciciosCore = new ArrayList<>();
        ejerciciosCore.add(new Ejercicio("Plancha", 4, 30, "Ejercicio isométrico para abdomen.", android.R.drawable.ic_menu_gallery));
        ejerciciosCore.add(new Ejercicio("Abdominales", 3, 20, "Clásico ejercicio de abdomen.", android.R.drawable.ic_menu_gallery));
        ejerciciosCore.add(new Ejercicio("Mountain Climbers", 4, 20, "Activa abdomen y cardio.", android.R.drawable.ic_menu_gallery));

        sesiones.add(new Sesion("Sesión 3: Core", ejerciciosCore));

        return sesiones;
    }

    private View crearVistaSesion(Sesion sesion) {
        LinearLayout sesionLayout = new LinearLayout(this);
        sesionLayout.setOrientation(LinearLayout.VERTICAL);
        sesionLayout.setBackgroundColor(0xFFFFFFFF);
        sesionLayout.setPadding(24, 24, 24, 24);

        LinearLayout.LayoutParams paramsSesion = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        paramsSesion.setMargins(0, 0, 0, 32);
        sesionLayout.setLayoutParams(paramsSesion);
        sesionLayout.setElevation(6);

        // 🔹 Título
        TextView tituloSesion = new TextView(this);
        tituloSesion.setText(sesion.getTitulo());
        tituloSesion.setTextSize(18f);
        tituloSesion.setTextColor(0xFF0C3264);
        sesionLayout.addView(tituloSesion);

        // 🔹 Botón
        Button botonSeleccionar = new Button(this);
        botonSeleccionar.setText("Ver ejercicios");
        botonSeleccionar.setBackgroundColor(0xFF05A3CB);
        botonSeleccionar.setTextColor(0xFFFFFFFF);
        sesionLayout.addView(botonSeleccionar);

        // 🔹 Lista ejercicios
        LinearLayout listaEjercicios = new LinearLayout(this);
        listaEjercicios.setOrientation(LinearLayout.VERTICAL);
        listaEjercicios.setVisibility(View.GONE);
        listaEjercicios.setPadding(16, 16, 16, 16);

        for (Ejercicio e : sesion.getEjercicios()) {
            listaEjercicios.addView(crearVistaEjercicio(e));
        }

        sesionLayout.addView(listaEjercicios);

        // 🔹 Toggle mostrar/ocultar
        botonSeleccionar.setOnClickListener(v -> {
            if (listaEjercicios.getVisibility() == View.GONE) {
                listaEjercicios.setVisibility(View.VISIBLE);
                botonSeleccionar.setText("Ocultar ejercicios");
            } else {
                listaEjercicios.setVisibility(View.GONE);
                botonSeleccionar.setText("Ver ejercicios");
            }
        });

        return sesionLayout;
    }

    private View crearVistaEjercicio(Ejercicio e) {
        LinearLayout tarjeta = new LinearLayout(this);
        tarjeta.setOrientation(LinearLayout.HORIZONTAL);
        tarjeta.setPadding(8, 8, 8, 8);
        tarjeta.setBackgroundColor(0xFFE9E9F3);
        tarjeta.setElevation(4);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 8, 0, 8);
        tarjeta.setLayoutParams(params);

        ImageView imagen = new ImageView(this);
        imagen.setImageResource(e.getImagenResId());
        LinearLayout.LayoutParams paramsImg = new LinearLayout.LayoutParams(150, 150);
        paramsImg.setMargins(0, 0, 16, 0);
        imagen.setLayoutParams(paramsImg);
        tarjeta.addView(imagen);

        LinearLayout textoContainer = new LinearLayout(this);
        textoContainer.setOrientation(LinearLayout.VERTICAL);

        TextView nombre = new TextView(this);
        nombre.setText(e.getNombre());
        nombre.setTextColor(0xFF15114D);
        nombre.setTextSize(16f);

        TextView detalle = new TextView(this);
        detalle.setText(e.getSeries() + " series x " + e.getRepeticiones() + " repeticiones\n" + e.getDescripcion());
        detalle.setTextColor(0xFF0C3264);
        detalle.setTextSize(14f);

        textoContainer.addView(nombre);
        textoContainer.addView(detalle);
        tarjeta.addView(textoContainer);

        return tarjeta;
    }
}
