package com.example.phoner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    TextView ihave;
    EditText email,password;
    Button signup;
    FirebaseAuth mfirebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    AccessTokenTracker accessTokenTracker;

    //fb
    private CallbackManager callbackManager;
    private TextView usert;
    private ImageView slika;
    private LoginButton loginFBButton;
    private static final String TAG="Facebook Auth";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sokrij go actionbarot
        getSupportActionBar().hide();


        //fb
        FacebookSdk.sdkInitialize(getApplicationContext());
        loginFBButton=findViewById(R.id.login_button);
        loginFBButton.setReadPermissions("email","public_profile");
        callbackManager=CallbackManager.Factory.create();
        loginFBButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,"onSucc"+loginResult);
                handleFacebookToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                Log.d(TAG,"onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG,"onError"+error);
            }
        });

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                }
                else{
                }
            }
        };

        accessTokenTracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken==null){
                    mfirebaseAuth.signOut();
                }
            }
        };
        
        ihave=findViewById(R.id.ihaveTV);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pasword);
        signup=findViewById(R.id.signupbutton);
        mfirebaseAuth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail=email.getText().toString();
                String spassword=password.getText().toString();
                if(semail.isEmpty()){
                    email.setError("Please enter mail");
                    email.requestFocus();
                }
                else if(spassword.isEmpty()){
                    password.setError("Please enter password");
                    password.requestFocus();

                }
                else if(semail.isEmpty() && spassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if(!semail.isEmpty() && !spassword.isEmpty()){
                    mfirebaseAuth.createUserWithEmailAndPassword(semail,spassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Signup unsucc,please try again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intent=new Intent(MainActivity.this,ListPhonesActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ihave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }

    private void handleFacebookToken(AccessToken token) {
        AuthCredential credential= FacebookAuthProvider.getCredential(token.getToken());
        mfirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "login with credental:succ", Toast.LENGTH_SHORT).show();
                    FirebaseUser user=mfirebaseAuth.getCurrentUser();

                    Intent i=new Intent(getApplicationContext(),ListPhonesActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "login with credental:failed", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        mfirebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null){
            mfirebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}