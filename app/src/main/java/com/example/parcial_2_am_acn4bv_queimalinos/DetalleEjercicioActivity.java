package com.example.parcial_2_am_acn4bv_queimalinos;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

public class DetalleEjercicioActivity extends AppCompatActivity {

    // Mapa de nombre ejercicio (español) → URL de tu imagen favorita en Unsplash
    private static final Map<String, String> EXERCISE_TO_IMAGE_URL = new HashMap<>();
    static {
        EXERCISE_TO_IMAGE_URL.put("Sentadillas", "https://plus.unsplash.com/premium_photo-1661374894884-52d7f260cd97?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        EXERCISE_TO_IMAGE_URL.put("Plancha", "https://images.unsplash.com/photo-1626444231642-6bd985bca16a?q=80&w=1169&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        EXERCISE_TO_IMAGE_URL.put("Mountain Climbers", "https://images.unsplash.com/photo-1540474211005-7c8a448f69e6?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        EXERCISE_TO_IMAGE_URL.put("Curl de bíceps", "https://images.unsplash.com/photo-1759300642647-cfe1e9bf7225?q=80&w=1074&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        EXERCISE_TO_IMAGE_URL.put("Tríceps fondo", "https://plus.unsplash.com/premium_photo-1664300574249-6fe665a644bd?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        EXERCISE_TO_IMAGE_URL.put("Abdominales", "https://images.unsplash.com/photo-1758875569284-c57e79ef75e0?q=80&w=1332&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        EXERCISE_TO_IMAGE_URL.put("Flexiones", "https://images.unsplash.com/photo-1731341400836-baaa5535b8d5?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        EXERCISE_TO_IMAGE_URL.put("Peso muerto", "https://plus.unsplash.com/premium_photo-1663134196858-317927dda72c?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        EXERCISE_TO_IMAGE_URL.put("Zancadas", "https://plus.unsplash.com/premium_photo-1664299888383-a1ba33510b91?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ejercicio);

        TextView titulo = findViewById(R.id.txtTituloEjercicio);
        TextView descripcion = findViewById(R.id.txtDescripcionEjercicio);
        ImageView imagen = findViewById(R.id.imgEjercicio);

        String nombre = getIntent().getStringExtra("nombre");
        String desc = getIntent().getStringExtra("descripcion");

        titulo.setText(nombre);
        descripcion.setText(desc);

        // Cargar imagen desde URL estática
        String imageUrl = EXERCISE_TO_IMAGE_URL.getOrDefault(nombre,
                "https://via.placeholder.com/400"); // fallback
        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imagen);
    }
}
