package com.example.parcial_1_am_acn4bv_queimalinos;

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

        // Evento del botón
        Button botonPrueba = findViewById(R.id.botonPrueba);
        botonPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "¡Botón presionado!", Toast.LENGTH_SHORT).show();
            }
        });

        // Elemento dinámico creado desde Java
        Button botonDinamico = new Button(this);
        botonDinamico.setText("Botón dinámico");
        ((LinearLayout) findViewById(R.id.contenedorPrincipal)).addView(botonDinamico);

        botonDinamico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "¡Botón dinámico presionado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
