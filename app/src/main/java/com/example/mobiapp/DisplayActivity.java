package com.example.mobiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DisplayActivity extends AppCompatActivity {
 TextView userEmail;
 Button userLogout;

 FirebaseAuth firebaseAuth;
 FirebaseUser firebaseUser;//giving us access to all the user's login details
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        userEmail = findViewById(R.id.DisplayEmail);
        userLogout = findViewById(R.id.LogOut);
        firebaseAuth =FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userEmail.setText(firebaseUser.getEmail());
        userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             firebaseAuth.getInstance().signOut();//when you all this method,the user will logout and when he try to acces the app he have to login again
           startActivity(new Intent(DisplayActivity.this,MainActivity.class));
            }
        });
    }
}
