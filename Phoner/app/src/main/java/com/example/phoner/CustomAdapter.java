package com.example.phoner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.BitSet;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> names,brands,displays,fcameras,rcameras,bateries,memories;
    ArrayList<byte[]> images;

    CustomAdapter(Context context,ArrayList<String> names, ArrayList<String> brands,ArrayList<String> displays,
            ArrayList<String> fcameras,ArrayList<String> rcameras,ArrayList<String> bateries,ArrayList<String> memories, ArrayList<byte[]> images ){
        this.context=context;
        this.names=names;
        this.brands=brands;
        this.displays=displays;
        this.fcameras=fcameras;
        this.rcameras=rcameras;
        this.bateries=bateries;
        this.memories=memories;
        this.images=images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.names_txt.setText(String.valueOf(names.get(position)));
        holder.imgView.setImageBitmap(ByteArrayToBitMap(images.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, SpecificationsActivity.class);
                i.putExtra("name",String.valueOf(names.get(position)));
                i.putExtra("brand",String.valueOf(brands.get(position)));
                i.putExtra("display",String.valueOf(displays.get(position)));
                i.putExtra("front_camera",String.valueOf(fcameras.get(position)));
                i.putExtra("rear_camera",String.valueOf(rcameras.get(position)));
                i.putExtra("battery_capacity",String.valueOf(bateries.get(position)));
                i.putExtra("memory",String.valueOf(memories.get(position)));
                i.putExtra("img",images.get(position));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView names_txt;
        ImageView imgView;

        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            names_txt=itemView.findViewById(R.id.name_txt);
            imgView=itemView.findViewById(R.id.imageviewROW);

            mainLayout=itemView.findViewById(R.id.mainLayout);

        }
    }

    public Bitmap ByteArrayToBitMap(byte[] bajtarej){
        Bitmap bmp= BitmapFactory.decodeByteArray(bajtarej,0,bajtarej.length);
        //ByteArrayOutputStream stream=new ByteArrayOutputStream();
        //bmp.compress(Bitmap.CompressFormat.PNG,100,stream);
        return bmp;
    }
}
