package com.example.parcial_1_am_acn4bv_queimalinos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout contenedorSesiones = findViewById(R.id.contenedorSesiones);

        // Mock data de sesiones
        String[] sesiones = {"Sesión 1: Piernas", "Sesión 2: Brazos", "Sesión 3: Core"};

        for (String sesion : sesiones) {
            // Contenedor de la sesión
            LinearLayout sesionLayout = new LinearLayout(this);
            sesionLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams paramsSesion = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            paramsSesion.setMargins(0, 0, 0, 24);
            sesionLayout.setLayoutParams(paramsSesion);
            sesionLayout.setPadding(16,16,16,16);
            sesionLayout.setBackgroundColor(0xFFEFEFEF);

            // Título de la sesión
            TextView tituloSesion = new TextView(this);
            tituloSesion.setText(sesion);
            tituloSesion.setTextSize(18f);
            tituloSesion.setPadding(0,0,0,8);
            sesionLayout.addView(tituloSesion);

            // Botón para seleccionar sesión
            Button botonSeleccionar = new Button(this);
            botonSeleccionar.setText("Seleccionar sesión");
            sesionLayout.addView(botonSeleccionar);

            // Lista de ejercicios mock (solo visibles al presionar botón)
            LinearLayout listaEjercicios = new LinearLayout(this);
            listaEjercicios.setOrientation(LinearLayout.VERTICAL);
            listaEjercicios.setVisibility(View.GONE); // Oculto por defecto
            listaEjercicios.setPadding(16,8,16,8);

            // Mock de ejercicios
            for (int i = 1; i <= 3; i++) {
                TextView ejercicio = new TextView(this);
                ejercicio.setText("Ejercicio " + i + ": 3 series x 12 repeticiones\nBreve descripción del ejercicio...");
                ejercicio.setPadding(0,0,0,8);
                listaEjercicios.addView(ejercicio);
            }

            sesionLayout.addView(listaEjercicios);

            // Evento del botón para mostrar/ocultar ejercicios
            botonSeleccionar.setOnClickListener(v -> {
                if (listaEjercicios.getVisibility() == View.GONE) {
                    listaEjercicios.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "Sesión seleccionada", Toast.LENGTH_SHORT).show();
                } else {
                    listaEjercicios.setVisibility(View.GONE);
                }
            });

            contenedorSesiones.addView(sesionLayout);
        }
    }
}
