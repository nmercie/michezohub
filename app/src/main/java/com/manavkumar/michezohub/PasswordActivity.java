package com.manavkumar.michezohub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailAddress;
    private Button reset;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        emailAddress = findViewById(R.id.password_reset);
        reset = findViewById(R.id.btnReset);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = emailAddress.getText().toString().trim();

        if (email.isEmpty()){
            emailAddress.setError("Email Address is required");
            emailAddress.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailAddress.setError("Please provide a valid email");
            emailAddress.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(PasswordActivity.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(PasswordActivity.this, "Tru again! Something went wrong!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}