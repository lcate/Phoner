package com.example.phoner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emaillogin,passwordlogin;
    TextView idonthave;
    Button signin;
    FirebaseAuth mfireBaseAuth;
    FirebaseAuth.AuthStateListener mAuthstateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //sokrij go actionbarot
        getSupportActionBar().hide();

        idonthave=findViewById(R.id.idonthaveTV);
        emaillogin=findViewById(R.id.emaillogin);
        passwordlogin=findViewById(R.id.paswordlogin);
        signin=findViewById(R.id.signinbutton);
        mfireBaseAuth=FirebaseAuth.getInstance();

        mAuthstateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mfireBaseUser=mfireBaseAuth.getCurrentUser();
                if(mfireBaseUser!=null){
                    Toast.makeText(LoginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,ListPhonesActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Please login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail=emaillogin.getText().toString();
                String spassword=passwordlogin.getText().toString();
                if(semail.isEmpty()){
                    emaillogin.setError("Please enter mail");
                    emaillogin.requestFocus();
                }
                else if(spassword.isEmpty()){
                    passwordlogin.setError("Please enter password");
                    passwordlogin.requestFocus();

                }
                else if(semail.isEmpty() && spassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if(!semail.isEmpty() && !spassword.isEmpty()){
                    mfireBaseAuth.signInWithEmailAndPassword(semail,spassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login error, please try again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent i=new Intent(LoginActivity.this,ListPhonesActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(LoginActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        idonthave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mfireBaseAuth.addAuthStateListener(mAuthstateListener);
    }
}