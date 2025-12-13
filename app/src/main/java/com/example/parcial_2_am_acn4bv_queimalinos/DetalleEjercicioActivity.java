package com.example.parcial_2_am_acn4bv_queimalinos;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

public class DetalleEjercicioActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ejercicio);

        TextView titulo = findViewById(R.id.txtTituloEjercicio);
        TextView descripcion = findViewById(R.id.txtDescripcionEjercicio);
        ImageView imagen = findViewById(R.id.imgEjercicio);

        db = FirebaseFirestore.getInstance();

        String ejercicioDocId = getIntent().getStringExtra("ejercicioDocId");
        if (ejercicioDocId == null) {
            Toast.makeText(this, "Ejercicio inválido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        db.collection("ejercicios")
                .document(ejercicioDocId)
                .get()
                .addOnSuccessListener(doc -> cargarEjercicio(doc, titulo, descripcion, imagen))
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error cargando ejercicio", Toast.LENGTH_SHORT).show()
                );
    }

    private void cargarEjercicio(
            DocumentSnapshot doc,
            TextView titulo,
            TextView descripcion,
            ImageView imagen
    ) {
        if (!doc.exists()) {
            finish();
            return;
        }

        titulo.setText(doc.getString("nombre"));
        descripcion.setText(doc.getString("descripcion"));

        String imageUrl = doc.getString("imageUrl");

        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imagen);
    }
}
