package com.example.parcial_1_am_acn4bv_queimalinos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput, passInput;
    private Button loginBtn, goRegisterBtn;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        emailInput = findViewById(R.id.emailInput);
        passInput = findViewById(R.id.passInput);
        loginBtn = findViewById(R.id.loginBtn);
        goRegisterBtn = findViewById(R.id.goRegisterBtn);

        loginBtn.setOnClickListener(v -> login());

        goRegisterBtn.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class))
        );
    }

    private void login() {
        String email = emailInput.getText().toString().trim();
        String pass = passInput.getText().toString().trim();

        auth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener(r -> {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
