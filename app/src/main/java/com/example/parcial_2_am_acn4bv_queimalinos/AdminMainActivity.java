package com.example.parcial_2_am_acn4bv_queimalinos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminMainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private TextView txtUsuarios, txtSesiones, txtEjercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        TextView titulo = findViewById(R.id.txtTitulo);
        titulo.setText("Panel Administrador");

        Button logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        txtUsuarios = findViewById(R.id.txtUsuarios);
        txtSesiones = findViewById(R.id.txtSesiones);
        txtEjercicios = findViewById(R.id.txtEjercicios);

        cargarTotales();
    }

    private void cargarTotales() {
        db.collection("usuarios").get()
                .addOnSuccessListener(q -> txtUsuarios.setText("Usuarios: " + q.size()));

        db.collection("sesiones").get()
                .addOnSuccessListener(q -> txtSesiones.setText("Sesiones: " + q.size()));

        db.collection("ejercicios").get()
                .addOnSuccessListener(q -> txtEjercicios.setText("Ejercicios: " + q.size()));
    }
}
