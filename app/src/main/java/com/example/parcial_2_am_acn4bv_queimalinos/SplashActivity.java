package com.example.parcial_2_am_acn4bv_queimalinos;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        FirebaseFirestore.getInstance()
                .collection("usuarios")
                .whereEqualTo("email", email)
                .limit(1)
                .get()
                .addOnSuccessListener(q -> {
                    if (q.isEmpty()) {
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                        return;
                    }

                    String rol = q.getDocuments().get(0).getString("rol");
                    Intent i;

                    if ("admin".equals(rol)) {
                        i = new Intent(this, AdminMainActivity.class);
                    } else if ("entrenador".equals(rol)) {
                        i = new Intent(this, EntrenadorMainActivity.class);
                    } else {
                        i = new Intent(this, MainActivity.class);
                    }

                    startActivity(i);
                    finish();
                });
    }
}
