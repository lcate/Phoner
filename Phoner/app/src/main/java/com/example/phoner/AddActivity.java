package com.example.phoner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {
    EditText name_input,brand_input,display_input,fcamera_input,rcamera_input,battery_input,memory_input;
    Button add_button,takePhoto;
    ImageView imageView;
    byte[] img;

    static int GALLERY_CODE=1;
    static int CAMERA_CODE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);



        name_input=findViewById(R.id.name_txt);
        brand_input=findViewById(R.id.brand_txt);
        display_input=findViewById(R.id.display_txt);
        fcamera_input=findViewById(R.id.fcamera_txt);
        rcamera_input=findViewById(R.id.rcamera_txt);
        battery_input=findViewById(R.id.battery_txt);
        memory_input=findViewById(R.id.memory_txt);
        imageView=findViewById(R.id.imageView);

        takePhoto=findViewById(R.id.takeAPhoto);

        img=imageViewToByte(imageView);



        //button to take pic
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_CODE);
            }
        });

        //add button
        add_button=findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBaseHelper myDB=new MyDataBaseHelper(AddActivity.this);
                Toast.makeText(AddActivity.this, name_input.getText().toString(), Toast.LENGTH_SHORT).show();
                myDB.addPhone(name_input.getText().toString(),brand_input.getText().toString(),
                        display_input.getText().toString(),fcamera_input.getText().toString(),
                        rcamera_input.getText().toString(),
                        battery_input.getText().toString(),memory_input.getText().toString(),img);
                Intent intent=new Intent(AddActivity.this,ListPhonesActivity.class);
                startActivity(intent);
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_CODE && resultCode==RESULT_OK && data!=null){
            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            img=imageViewToByte(imageView);
        }

    }

    public byte[] imageViewToByte(ImageView image){
        Bitmap bitmap=((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytes=stream.toByteArray();
        return bytes;
    }
}