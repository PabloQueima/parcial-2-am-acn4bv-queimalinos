package com.example.parcial_2_am_acn4bv_queimalinos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailInput, passInput;
    private Button registerBtn;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        emailInput = findViewById(R.id.emailInput);
        passInput = findViewById(R.id.passInput);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(v -> register());
    }

    private void register() {
        String email = emailInput.getText().toString().trim();
        String pass = passInput.getText().toString().trim();

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Completar email y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener(r -> {
                    FirebaseUser user = auth.getCurrentUser();
                    if (user == null) {
                        Toast.makeText(this, "Error inesperado.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Datos a guardar en Firestore
                    Map<String, Object> datos = new HashMap<>();
                    datos.put("email", email);
                    datos.put("rol", "cliente");
                    datos.put("sesionesAsignadas", new java.util.ArrayList<>());
                    datos.put("createdAt", System.currentTimeMillis());

                    db.collection("usuarios")
                            .document(user.getUid())
                            .set(datos)
                            .addOnSuccessListener(unused -> {
                                Intent i = new Intent(this, MainActivity.class);
                                startActivity(i);
                                finish();
                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(this, "Error guardando usuario: " + e.getMessage(), Toast.LENGTH_LONG).show()
                            );
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
    }
}
