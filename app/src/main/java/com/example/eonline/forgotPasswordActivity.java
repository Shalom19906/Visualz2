package com.example.eonline;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.rengwuxian.materialedittext.MaterialEditText;


public class forgotPasswordActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private TextView resetState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

      

        mAuth = FirebaseAuth.getInstance();
        EditText emailAddress = findViewById(R.id.emaill);
        resetState = findViewById(R.id.resetText);
        Button resetPasswordBtn = findViewById(R.id.sendMessage);
        progressBar = findViewById(R.id.progressbar);
        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                mAuth.fetchSignInMethodsForEmail(emailAddress.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.getResult().getSignInMethods().isEmpty()){
                            progressBar.setVisibility(View.GONE);
                            resetState.setText("This is not an registered email, you can create new account");
                    }else {
                            mAuth.sendPasswordResetEmail(emailAddress.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()){
                                resetState.setText("An email has been sent to reset password");
                            }else{
                                    resetState.setText(task.getException().getMessage());
                                }
                            }
                        });
                    }
                    }
                    });
                }

    });
}
}





