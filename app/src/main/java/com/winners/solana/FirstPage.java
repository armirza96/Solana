package com.winners.solana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class FirstPage extends AppCompatActivity {

    private Button Login;
    private  Button Signup;
    private EditText username;
    private EditText password;

    private FirebaseUser mUser;
    private DatabaseReference databaseReference;

    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstPage.this,MainActivity.class));

                String u = username.getText().toString().trim();
                String p = password.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(u, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            Intent intent = new Intent(FirstPage.this, MainActivity.class);
                            startActivity(intent);

                            finish();
                        } else {
                            Toast.makeText(FirstPage.this, "Account not logged in", Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }

        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstPage.this,SignUp2.class));

            }
        });
    }

}