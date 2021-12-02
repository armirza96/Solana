package com.winners.solana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class FirstPage extends AppCompatActivity {

    private Button Login;
    private  Button Signup;
    private EditText username;
    private EditText password;

    private FirebaseUser mUser;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        getSupportActionBar().hide();
        setTitle("Home Page");
//        button = findViewById(R.id.Goto);
        Login = findViewById(R.id.Login);
        Signup = findViewById(R.id.Signup);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        username.getText().toString().trim();
        password.getText().toString().trim();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstPage.this,MainActivity.class));
            }

        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstPage.this,SignupActivity.class));

            }
        });
    }

}