package com.example.phoner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

public class UseroActivity extends AppCompatActivity {

    TextView u,e,n;
    ImageView slika;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usero);

        u=findViewById(R.id.useroT);
        e=findViewById(R.id.emailoT);
        n=findViewById(R.id.phoneto);
        slika=findViewById(R.id.slikatA);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        String name = user.getDisplayName();
        String email=user.getEmail();
        String phone=user.getPhoneNumber();
        e.setText(email);
        u.setText(name);
        n.setText(phone);

        if(user.getPhotoUrl()!=null){
            String url=user.getPhotoUrl().toString();
            url=url+"?type=large";
            Picasso.get().load(url).into(slika);
        }
    }


}