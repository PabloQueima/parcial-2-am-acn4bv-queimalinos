package com.example.parcial_1_am_acn4bv_queimalinos;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

        // 🔹 Generar datos mock
        List<Sesion> sesiones = generarSesionesMock();

        // 🔹 Renderizar cada sesión
        for (Sesion sesion : sesiones) {
            contenedorSesiones.addView(crearVistaSesion(sesion));
        }
    }

    // -------------------------------------------------
    // 🔸 Generar datos de prueba (mock)
    // -------------------------------------------------
    private List<Sesion> generarSesionesMock() {
        List<Sesion> sesiones = new ArrayList<>();

        // Sesión 1: Piernas
        List<Ejercicio> ejerciciosPiernas = new ArrayList<>();
        ejerciciosPiernas.add(new Ejercicio(
                getString(R.string.ejercicio_sentadillas),
                3, 12,
                getString(R.string.desc_sentadillas),
                android.R.drawable.ic_menu_gallery
        ));
        ejerciciosPiernas.add(new Ejercicio(
                getString(R.string.ejercicio_zancadas),
                3, 10,
                getString(R.string.desc_zancadas),
                android.R.drawable.ic_menu_gallery
        ));
        ejerciciosPiernas.add(new Ejercicio(
                getString(R.string.ejercicio_peso_muerto),
                4, 8,
                getString(R.string.desc_peso_muerto),
                android.R.drawable.ic_menu_gallery
        ));

        sesiones.add(new Sesion(getString(R.string.sesion_piernas), ejerciciosPiernas));

        // Sesión 2: Brazos
        List<Ejercicio> ejerciciosBrazos = new ArrayList<>();
        ejerciciosBrazos.add(new Ejercicio(
                getString(R.string.ejercicio_flexiones),
                3, 15,
                getString(R.string.desc_flexiones),
                android.R.drawable.ic_menu_gallery
        ));
        ejerciciosBrazos.add(new Ejercicio(
                getString(R.string.ejercicio_curl_biceps),
                4, 10,
                getString(R.string.desc_biceps),
                android.R.drawable.ic_menu_gallery
        ));
        ejerciciosBrazos.add(new Ejercicio(
                getString(R.string.ejercicio_triceps_fondo),
                3, 12,
                getString(R.string.desc_triceps),
                android.R.drawable.ic_menu_gallery
        ));

        sesiones.add(new Sesion(getString(R.string.sesion_brazos), ejerciciosBrazos));

        // Sesión 3: Core
        List<Ejercicio> ejerciciosCore = new ArrayList<>();
        ejerciciosCore.add(new Ejercicio(
                getString(R.string.ejercicio_plancha),
                4, 30,
                getString(R.string.desc_plancha),
                android.R.drawable.ic_menu_gallery
        ));
        ejerciciosCore.add(new Ejercicio(
                getString(R.string.ejercicio_abdominales),
                3, 20,
                getString(R.string.desc_abdominales),
                android.R.drawable.ic_menu_gallery
        ));
        ejerciciosCore.add(new Ejercicio(
                getString(R.string.ejercicio_mountain_climbers),
                4, 20,
                getString(R.string.desc_mountain),
                android.R.drawable.ic_menu_gallery
        ));

        sesiones.add(new Sesion(getString(R.string.sesion_core), ejerciciosCore));

        return sesiones;
    }

    // -------------------------------------------------
    // 🔸 Crear vista de una sesión
    // -------------------------------------------------
    private View crearVistaSesion(Sesion sesion) {
        LinearLayout sesionLayout = new LinearLayout(this);
        sesionLayout.setOrientation(LinearLayout.VERTICAL);
        sesionLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.color_light));
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
        tituloSesion.setTextColor(ContextCompat.getColor(this, R.color.color_mid_dark));
        sesionLayout.addView(tituloSesion);

        // 🔹 Botón mostrar ejercicios
        Button botonSeleccionar = new Button(this);
        botonSeleccionar.setText(getString(R.string.boton_ver_ejercicios));
        botonSeleccionar.setBackgroundColor(ContextCompat.getColor(this, R.color.color_primary));
        botonSeleccionar.setTextColor(ContextCompat.getColor(this, R.color.color_light));
        sesionLayout.addView(botonSeleccionar);

        // 🔹 Lista de ejercicios
        LinearLayout listaEjercicios = new LinearLayout(this);
        listaEjercicios.setOrientation(LinearLayout.VERTICAL);
        listaEjercicios.setVisibility(View.GONE);
        listaEjercicios.setPadding(16, 16, 16, 16);

        for (Ejercicio e : sesion.getEjercicios()) {
            listaEjercicios.addView(crearVistaEjercicio(e));
        }

        sesionLayout.addView(listaEjercicios);

        // 🔹 Evento de mostrar/ocultar ejercicios
        botonSeleccionar.setOnClickListener(v -> {
            if (listaEjercicios.getVisibility() == View.GONE) {
                listaEjercicios.setVisibility(View.VISIBLE);
                botonSeleccionar.setText(getString(R.string.boton_ocultar_ejercicios));
            } else {
                listaEjercicios.setVisibility(View.GONE);
                botonSeleccionar.setText(getString(R.string.boton_ver_ejercicios));
            }
        });

        return sesionLayout;
    }

    // -------------------------------------------------
    // 🔸 Crear vista de un ejercicio
    // -------------------------------------------------
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
        nombre.setTextColor(ContextCompat.getColor(this, R.color.color_dark));
        nombre.setTextSize(16f);

        TextView detalle = new TextView(this);
        detalle.setText(
                e.getSeries() + " series x " + e.getRepeticiones() + " repeticiones\n" + e.getDescripcion()
        );
        detalle.setTextColor(ContextCompat.getColor(this, R.color.color_mid_dark));
        detalle.setTextSize(14f);

        textoContainer.addView(nombre);
        textoContainer.addView(detalle);
        tarjeta.addView(textoContainer);

        return tarjeta;
    }
}
