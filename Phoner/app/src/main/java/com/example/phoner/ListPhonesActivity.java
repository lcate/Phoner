package com.example.phoner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ListPhonesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    RecyclerView recyclerView;
    FloatingActionButton add_button,viewProfile;

    ArrayList<byte[]> images;
    ArrayList<String> names,brands,displays,fcameras,rcameras,bateries,memories;
    
    MyDataBaseHelper myDB;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_phones);


        recyclerView=findViewById(R.id.recycler_view);

        add_button=findViewById(R.id.add_FAbutton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddActivity.class);
                startActivity(intent);
            }
        });

        viewProfile=findViewById(R.id.goProfile);
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),UseroActivity.class);
                startActivity(i);
            }
        });

        myDB=new MyDataBaseHelper(ListPhonesActivity.this);
        names=new ArrayList<>();
        brands=new ArrayList<>();
        displays=new ArrayList<>();
        fcameras=new ArrayList<>();
        rcameras=new ArrayList<>();
        bateries=new ArrayList<>();
        memories=new ArrayList<>();
        images=new ArrayList<>();

        storeDefaultData();
        storeDataInArrays();
        appendDataToRecyclerView(names,brands,displays,fcameras,rcameras,bateries,memories,images);
    }

    //stavanje na podatocite vo recyclerview
    private void appendDataToRecyclerView(ArrayList<String> names, ArrayList<String> brands, ArrayList<String> displays, ArrayList<String> fcameras, ArrayList<String> rcameras, ArrayList<String> bateries, ArrayList<String> memories, ArrayList<byte[]> images) {

        customAdapter=new CustomAdapter(ListPhonesActivity.this,names,brands,displays,fcameras,rcameras,bateries,memories,images);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListPhonesActivity.this));

    }
    void storeDefaultData(){
        Resources res=getResources();
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        Drawable d=res.getDrawable(R.drawable.eden);
        Bitmap bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] slika_eden = stream.toByteArray();
        names.add("Samsung Galaxy Z Flip");
        brands.add("Samsung");
        displays.add("6.7 inches");
        fcameras.add("10 MP");
        rcameras.add("12 MP+12 MP");
        bateries.add("Li-Po 3300 mAh");
        memories.add("256GB; 8GB RAM");
        images.add(slika_eden);


        ByteArrayOutputStream stream1= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.dva);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream1);
        byte[] slika_dva = stream1.toByteArray();
        names.add("Huawei P40 Pro");
        brands.add("Huawei");
        displays.add("6.58 inches");
        fcameras.add("32 MP");
        rcameras.add("50 MP + 12 MP + 40 MP");
        bateries.add("Li-Po 4200 mAh");
        memories.add("128GB, 8GB RAM");
        images.add(slika_dva);

        ByteArrayOutputStream stream2= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.tri);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream2);
        byte[] slika_tri = stream2.toByteArray();
        names.add("Cat S61");
        brands.add("Cat");
        displays.add("5.2 inches");
        fcameras.add("8 MP");
        rcameras.add("16 MP");
        bateries.add("Li-Po 4200 mAh");
        memories.add("64GB 4GB RAM");
        images.add(slika_tri);

        ByteArrayOutputStream stream3= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.cetiri);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream3);
        byte[] slika_cetiri = stream3.toByteArray();
        names.add("Samsung Galaxy S20");
        brands.add("Samsung");
        displays.add("6.2 inches");
        fcameras.add("10 MP");
        rcameras.add("12 MP + 64 MP + 12 MP");
        bateries.add("Li-Po 4000 mAh");
        memories.add("128GB, 8GB RAM");
        images.add(slika_cetiri);


        ByteArrayOutputStream stream4= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.pet);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream4);
        byte[] slikapet = stream4.toByteArray();
        names.add("Apple iPhone 11");
        brands.add("Apple");
        displays.add("6.5 inches");
        fcameras.add("12 MP");
        rcameras.add("12 MP + 12 MP + 12 MP");
        bateries.add("Li-Po 4000 mAh");
        memories.add("256GB 4GB RAM");
        images.add(slikapet);

        ByteArrayOutputStream stream5= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.sest);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream5);
        byte[] slikasest = stream5.toByteArray();
        names.add("Xiaomi Mi Note 10 Pro");
        brands.add("Xiaomi");
        displays.add("6.47 inches");
        fcameras.add("32 MP");
        rcameras.add("108 MP + 12 MP + 20 MP + 8 MP + 2 MP");
        bateries.add("Li-Po 5260 mAh");
        memories.add("256GB 8GB RAM");
        images.add(slikasest);

        ByteArrayOutputStream stream6= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.sedum);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream6);
        byte[] slikasedum = stream6.toByteArray();
        names.add("LG Q6 Plus");
        brands.add("LG");
        displays.add("5.5 inches");
        fcameras.add("5 MP");
        rcameras.add("13 MP");
        bateries.add("Li-Po 3000 mAh");
        memories.add("32GB 3GB RAM");
        images.add(slikasedum);

        ByteArrayOutputStream stream7= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.devet);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream7);
        byte[] slikaosum = stream7.toByteArray();
        names.add("Xiaomi Redmi Note 9 Pro");
        brands.add("Xiaomi");
        displays.add("6.67 inches");
        fcameras.add("16 MP");
        rcameras.add("64MP + 8MP + 5MP + 2MP");
        bateries.add("Li-Po 5020 mAh");
        memories.add("128GB 6GB RAM");
        images.add(slikaosum);


    }

    void storeDataInArrays(){
        Cursor cursor=myDB.readAllData();
        if(cursor.getCount()==0){
        }
        else {
            while (cursor.moveToNext()){
                names.add(cursor.getString(1));
                brands.add(cursor.getString(2));
                displays.add(cursor.getString(3));
                fcameras.add(cursor.getString(4));
                rcameras.add(cursor.getString(5));
                bateries.add(cursor.getString(6));
                memories.add(cursor.getString(7));
                images.add(cursor.getBlob(8));
            }
        }
    }

    //action bar toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        MenuItem search=menu.findItem(R.id.search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ilogout:
                logOut();
                break;
            case R.id.ilocation:
                Intent intent=new Intent(ListPhonesActivity.this,MapsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //search filtero
    @Override
    public boolean onQueryTextChange(String newText) {
        newText=newText.toLowerCase();
        ArrayList<String> newnames=new ArrayList<>();
        ArrayList<String> newbrands=new ArrayList<>();
        ArrayList<String> newdisplys=new ArrayList<>();
        ArrayList<String> newfcameras=new ArrayList<>();
        ArrayList<String> newrcameras=new ArrayList<>();
        ArrayList<String> newbateries=new ArrayList<>();
        ArrayList<String> newmemories=new ArrayList<>();
        ArrayList<byte[]> newimg=new ArrayList<>();
        for(int i=0;i<names.size();i++){
            if(names.get(i).toLowerCase().contains(newText)){
                newnames.add(names.get(i));
                newbrands.add(brands.get(i));
                newdisplys.add(displays.get(i));
                newfcameras.add(fcameras.get(i));
                newrcameras.add(rcameras.get(i));
                newbateries.add(bateries.get(i));
                newmemories.add(memories.get(i));
                newimg.add(images.get(i));
            }
        }
        appendDataToRecyclerView(newnames,newbrands,newdisplys,newfcameras,newrcameras,newbateries,newmemories,newimg);
        return true;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }
    //^do tuka action bar toolbar



    @Override
    public void onBackPressed() {
        logOut();
    }


    private void logOut() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.alert_dark_frame)
                .setTitle("Logout Alert").setMessage("Are you sure you want to logout?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        LoginManager.getInstance().logOut();
                    }
                }).setNegativeButton("NO",null).show();
    }

}
