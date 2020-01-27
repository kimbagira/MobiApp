package com.example.mobiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText UserPassword;
    EditText UserEmail;
    Button UserLogin;
    FirebaseAuth firebaseAuth;// start to use firebase to enable the user to login to the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar2);
        UserEmail = findViewById(R.id.Email2);
        UserPassword = findViewById(R.id.Password2);
        UserLogin = findViewById(R.id.LogInButton2);

        firebaseAuth = firebaseAuth.getInstance();
        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(UserEmail.getText().toString(),UserPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {//calling one of the fireBaseAuth to login the user
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {//Check for the results based on the task(that means the email and password are already saved)(the task result will be successful
                        progressBar.setVisibility(View.GONE);//once the fireBase Aunthaticate the inputes visibility of this program to gone
                        if(task.isSuccessful()){// if the task is successful
                            startActivity(new Intent(LoginActivity.this,DisplayActivity.class));
                        }else{//if not successful,receive a toast whatever message  sent from the fireBase based on the task
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}