package com.winners.solana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class SignUp2 extends AppCompatActivity {

    private Button Signup;
    private EditText email;
    private EditText password;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private final String TAG = "SIGN_UP_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        mAuth = FirebaseAuth.getInstance();

        Signup = findViewById(R.id.btnSignUp);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                mAuth.createUserWithEmailAndPassword(Username, Password)
//                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            //updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(SignupActivity.this, "Authentication failed.", Toast.LENGTH_LONG).show();
//                            //Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//                            //updateUI(null);
//                        }
//                    }
//                });
                String Email =  email.getEditableText().toString();
                String Password = password.getEditableText().toString();

                mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            User user = new User(Email);

                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(Email)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                Toast.makeText(SignUp2.this, "User gotten", Toast.LENGTH_LONG).show();
//                                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
//                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(SignUp2.this, "User was not able to be successfully", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });
                        }
                        else {
                            Toast.makeText(SignUp2.this, "Account not Created", Toast.LENGTH_LONG).show();

                        }

                    }

                });
            }
        });
    }
}