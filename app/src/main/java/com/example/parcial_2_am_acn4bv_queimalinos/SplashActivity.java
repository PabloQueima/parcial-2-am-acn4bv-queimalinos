package com.example.parcial_2_am_acn4bv_queimalinos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Comprobar Firebase Auth
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // Comprobar SharedPreferences
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String email = prefs.getString("usuarioEmail", null);

        Intent i;
        if (user != null && email != null) {
            // Sesión activa: ir directo a MainActivity
            i = new Intent(this, MainActivity.class);
        } else {
            // No hay sesión: ir a LoginActivity
            i = new Intent(this, LoginActivity.class);
        }

        startActivity(i);
        finish(); // cerrar SplashActivity
    }
}

