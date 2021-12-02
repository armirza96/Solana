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
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private Button Signup;
    private EditText username;
    private EditText password;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        Signup = findViewById(R.id.Signup);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        String Username =  username.getEditableText().toString().trim();


        String Password = password.getEditableText().toString().trim();
//


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (Username.isEmpty()) {
//                    username.setError("Wrong Username");
//                    return;
//                }
//                if(Password.isEmpty()){
//                    password.setError("Wrong Password");
//                    return;
//                }

                mAuth.createUserWithEmailAndPassword(username.getEditableText().toString(), password.getEditableText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            User user = new User(Username);

                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(Username)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                Toast.makeText(SignupActivity.this, "Good", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(SignupActivity.this, "Good", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "Good", Toast.LENGTH_LONG).show();

                        }

                    }

                });

            }
        });



    }
}