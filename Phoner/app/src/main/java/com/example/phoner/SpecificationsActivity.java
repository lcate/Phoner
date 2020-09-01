package com.example.phoner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class SpecificationsActivity extends AppCompatActivity {
    TextView name_input,brand_input,display_input,fcamera_input,rcamera_input,battery_input,memory_input;
    Button update_button;
    String name,brand,display,fcamera,rcamera,batery,memory;
    ImageView image;
    byte[] img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifications);

        image=findViewById(R.id.image);

        name_input=findViewById(R.id.name_txt1);
        brand_input=findViewById(R.id.brand_txt1);
        display_input=findViewById(R.id.display_txt1);
        fcamera_input=findViewById(R.id.fcamera_txt1);
        rcamera_input=findViewById(R.id.rcamera_txt1);
        battery_input=findViewById(R.id.battery_txt1);
        memory_input=findViewById(R.id.memory_txt1);



        getAndSetIntentData();
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("name") && getIntent().hasExtra("brand") &&
                getIntent().hasExtra("display") && getIntent().hasExtra("front_camera") && getIntent().hasExtra("rear_camera") &&
                getIntent().hasExtra("battery_capacity") && getIntent().hasExtra("memory")){
            //Getting data from Intent
            name=getIntent().getStringExtra("name");
            brand=getIntent().getStringExtra("brand");
            display=getIntent().getStringExtra("display");
            fcamera=getIntent().getStringExtra("front_camera");
            rcamera=getIntent().getStringExtra("rear_camera");
            batery=getIntent().getStringExtra("battery_capacity");
            memory=getIntent().getStringExtra("memory");
            img=getIntent().getByteArrayExtra("img");

            //Setting intent data
            name_input.setText(name);
            brand_input.setText(brand);
            display_input.setText(display);
            fcamera_input.setText(fcamera);
            rcamera_input.setText(rcamera);
            battery_input.setText(batery);
            memory_input.setText(memory);
            image.setImageBitmap(ByteArrayToBitMap(img));

        }else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    public Bitmap ByteArrayToBitMap(byte[] bajtarej){
        Bitmap bmp= BitmapFactory.decodeByteArray(bajtarej,0,bajtarej.length);
        return bmp;
    }
}