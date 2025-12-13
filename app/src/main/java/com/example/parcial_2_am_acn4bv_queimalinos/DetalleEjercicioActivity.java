package com.example.parcial_2_am_acn4bv_queimalinos;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetalleEjercicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ejercicio);

        TextView titulo = findViewById(R.id.txtTituloEjercicio);
        TextView descripcion = findViewById(R.id.txtDescripcionEjercicio);
        ImageView imagen = findViewById(R.id.imgEjercicio);

        String nombre = getIntent().getStringExtra("nombre");
        String desc = getIntent().getStringExtra("descripcion");
        String imageUrl = getIntent().getStringExtra("imageUrl");

        titulo.setText(nombre != null ? nombre : "Ejercicio");
        descripcion.setText(desc != null ? desc : "Sin descripción");

        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imagen);
    }
}
