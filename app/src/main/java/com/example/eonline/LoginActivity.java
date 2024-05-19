package com.example.eonline;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    private EditText email,password;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    TextView tx;
    TextView tp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button registerBtn = findViewById(R.id.register);
        Button loginBtn = findViewById(R.id.login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressbar);
        firebaseAuth = FirebaseAuth.getInstance();
        tx= findViewById(R.id.t1);
        tp=findViewById(R.id.fp);

        tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, forgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener((v -> {
            String tex_email = email.getText().toString();
            String tex_password = password.getText().toString();
            if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)) {
                Toast.makeText(LoginActivity.this, "All Field required", Toast.LENGTH_SHORT).show();
            } else {
                login(tex_email, tex_password);
            }
        }));

    }
    private void login(String tex_email, String tex_password) {
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(tex_email, tex_password).addOnCompleteListener((task) -> {
            progressBar.setVisibility(View.GONE);
            if (task.isSuccessful()) {
                Toast.makeText(LoginActivity.this, "you are logged in successfully.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, Maing.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

