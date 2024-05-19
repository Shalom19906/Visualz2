package com.example.eonline;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {
    private EditText signup_email,  signup_pass, signup_confirm, signup_mobile;

    private Button registerBtn;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private TextView login;
    private DatabaseReference databaseReference;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        signup_email = findViewById(R.id.emailR);
        signup_pass = findViewById(R.id.passwordR);
        signup_confirm = findViewById(R.id.passwordCP);
        registerBtn = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressbar);

        login = findViewById(R.id.t2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = signup_email.getText().toString();
                final String txt_password = signup_pass.getText().toString();
                final String txt_conf = signup_confirm.getText().toString();


                    if ( TextUtils.isEmpty(email) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_conf)
                           ) {
                        Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    } else if (txt_password.equals(txt_conf)) {
                        register( email, txt_password, txt_conf);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Password and confirm password field do not match.", Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }




    private void register( String email, String txt_password, String txt_conf) {
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email, txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser rUser = firebaseAuth.getCurrentUser();
                    assert rUser != null;
                    String userId = rUser.getUid();
                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("userId", userId);

                    hashMap.put("email", email);
                    hashMap.put("password", txt_password);
                    hashMap.put("conf", txt_conf);


                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);

                                Toast.makeText(RegisterActivity.this, "you are authenticated successfully.", Toast.LENGTH_SHORT).show();


                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}



