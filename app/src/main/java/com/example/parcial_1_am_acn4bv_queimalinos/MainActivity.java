package com.example.parcial_1_am_acn4bv_queimalinos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout contenedor = findViewById(R.id.contenedorPrincipal);

        // Botón estático
        Button botonPrueba = findViewById(R.id.botonPrueba);
        botonPrueba.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "¡Botón presionado!", Toast.LENGTH_SHORT).show()
        );

        // Botón dinámico
        Button botonDinamico = new Button(this);
        botonDinamico.setText("Botón dinámico");
        botonDinamico.setBackgroundColor(Color.parseColor("#05A3CB"));
        botonDinamico.setTextColor(Color.WHITE);
        contenedor.addView(botonDinamico);

        botonDinamico.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "¡Botón dinámico presionado!", Toast.LENGTH_SHORT).show()
        );

        // Agregar varios botones dinámicos simulando ejercicios
        for (int i = 1; i <= 5; i++) {
            Button botonEjercicio = new Button(this);
            botonEjercicio.setText("Ejercicio " + i);
            botonEjercicio.setBackgroundColor(Color.parseColor("#BB81B6"));
            botonEjercicio.setTextColor(Color.WHITE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 8, 0, 8);
            botonEjercicio.setLayoutParams(params);
            contenedor.addView(botonEjercicio);

            final int index = i;
            botonEjercicio.setOnClickListener(v ->
                    Toast.makeText(MainActivity.this, "Seleccionaste el Ejercicio " + index, Toast.LENGTH_SHORT).show()
            );
        }
    }
}
