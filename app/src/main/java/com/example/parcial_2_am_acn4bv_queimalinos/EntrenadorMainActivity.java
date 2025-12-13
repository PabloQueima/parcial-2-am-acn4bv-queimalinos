package com.example.parcial_2_am_acn4bv_queimalinos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

public class EntrenadorMainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private LinearLayout contenedorSesiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenador_main);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        TextView titulo = findViewById(R.id.txtTitulo);
        titulo.setText("Panel Entrenador");

        Button logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        contenedorSesiones = findViewById(R.id.contenedorSesiones);

        String uid = auth.getCurrentUser().getUid();

        db.collection("usuarios")
                .document(uid)
                .get()
                .addOnSuccessListener(doc -> {
                    Long entrenadorId = doc.getLong("id");
                    if (entrenadorId == null) return;

                    db.collection("sesiones")
                            .whereEqualTo("entrenadorId", entrenadorId.intValue())
                            .get()
                            .addOnSuccessListener(sesiones -> {
                                for (DocumentSnapshot s : sesiones) {
                                    mostrarSesion(s);
                                }
                            });
                });
    }

    private void mostrarSesion(DocumentSnapshot sesion) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(24, 24, 24, 24);
        card.setBackgroundColor(ContextCompat.getColor(this, R.color.color_light));

        TextView titulo = new TextView(this);
        titulo.setText(sesion.getString("titulo"));
        titulo.setTextSize(18f);
        card.addView(titulo);

        TextView cliente = new TextView(this);
        cliente.setText("Cliente ID: " + sesion.getLong("clienteId"));
        card.addView(cliente);

        contenedorSesiones.addView(card);
    }
}
