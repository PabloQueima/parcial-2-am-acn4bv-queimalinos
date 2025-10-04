package com.example.parcial_1_am_acn4bv_queimalinos;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout contenedorSesiones = findViewById(R.id.contenedorSesiones);

        // Mock de sesiones
        String[] sesiones = {"Sesión 1: Piernas", "Sesión 2: Brazos", "Sesión 3: Core"};

        for (String sesion : sesiones) {
            // Layout de sesión
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

            // Título de sesión
            TextView tituloSesion = new TextView(this);
            tituloSesion.setText(sesion);
            tituloSesion.setTextSize(18f);
            tituloSesion.setTextColor(0xFF0C3264);
            tituloSesion.setPadding(0, 0, 0, 12);
            sesionLayout.addView(tituloSesion);

            // Botón de selección
            Button botonSeleccionar = new Button(this);
            botonSeleccionar.setText("Ver ejercicios");
            botonSeleccionar.setBackgroundColor(0xFF05A3CB);
            botonSeleccionar.setTextColor(0xFFFFFFFF);
            sesionLayout.addView(botonSeleccionar);

            // Contenedor de ejercicios (oculto inicialmente)
            LinearLayout listaEjercicios = new LinearLayout(this);
            listaEjercicios.setOrientation(LinearLayout.VERTICAL);
            listaEjercicios.setVisibility(View.GONE);
            listaEjercicios.setPadding(16, 16, 16, 16);

            // Mock de ejercicios con imagen + descripción
            for (int i = 1; i <= 3; i++) {
                LinearLayout tarjetaEjercicio = new LinearLayout(this);
                tarjetaEjercicio.setOrientation(LinearLayout.HORIZONTAL);
                tarjetaEjercicio.setPadding(8, 8, 8, 8);
                tarjetaEjercicio.setBackgroundColor(0xFFE9E9F3);
                tarjetaEjercicio.setElevation(4);

                LinearLayout.LayoutParams paramsTarjeta = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                paramsTarjeta.setMargins(0, 8, 0, 8);
                tarjetaEjercicio.setLayoutParams(paramsTarjeta);

                // Imagen mock (usa un ícono por ahora)
                ImageView imagen = new ImageView(this);
                imagen.setImageResource(android.R.drawable.ic_menu_gallery);
                LinearLayout.LayoutParams paramsImg = new LinearLayout.LayoutParams(150, 150);
                paramsImg.setMargins(0, 0, 16, 0);
                imagen.setLayoutParams(paramsImg);
                tarjetaEjercicio.addView(imagen);

                // Texto de descripción
                LinearLayout textoContainer = new LinearLayout(this);
                textoContainer.setOrientation(LinearLayout.VERTICAL);

                TextView titulo = new TextView(this);
                titulo.setText("Ejercicio " + i);
                titulo.setTextColor(0xFF15114D);
                titulo.setTextSize(16f);

                TextView detalle = new TextView(this);
                detalle.setText("3 series x 12 repeticiones\nBreve descripción del ejercicio...");
                detalle.setTextColor(0xFF0C3264);
                detalle.setTextSize(14f);

                textoContainer.addView(titulo);
                textoContainer.addView(detalle);
                tarjetaEjercicio.addView(textoContainer);

                listaEjercicios.addView(tarjetaEjercicio);
            }

            sesionLayout.addView(listaEjercicios);

            // Evento botón mostrar/ocultar
            botonSeleccionar.setOnClickListener(v -> {
                if (listaEjercicios.getVisibility() == View.GONE) {
                    listaEjercicios.setVisibility(View.VISIBLE);
                    botonSeleccionar.setText("Ocultar ejercicios");
                } else {
                    listaEjercicios.setVisibility(View.GONE);
                    botonSeleccionar.setText("Ver ejercicios");
                }
            });

            contenedorSesiones.addView(sesionLayout);
        }
    }
}
