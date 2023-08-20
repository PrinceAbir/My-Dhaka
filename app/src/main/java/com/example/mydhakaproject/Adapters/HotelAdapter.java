package com.example.mydhakaproject.Adapters;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mydhakaproject.Models.HotelModel;
import com.example.mydhakaproject.R;

import java.util.ArrayList;
import java.util.List;


public  class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.MyViewHolder> {


    Context context;
    List<HotelModel> list;
    Activity activity;



    public HotelAdapter(Context context,Activity activity, List<HotelModel> itemList){
        this.context = context;
        this.activity = activity;
        this.list = itemList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hotel_sample_view,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getHotel_image()).into(holder.hotel_image);
        holder.hotel_name.setText(list.get(position).getHotel_name());

        holder.itemView.startAnimation(AnimationUtils.loadAnimation(context,R.anim.move_left));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = activity.getIntent();
                String user_email = intent2.getStringExtra("user_email");


                HotelModel hotelModel= list.get(holder.getAdapterPosition());
                Intent intent = new Intent(activity, HotelView.class);

                int id = hotelModel.getHotel_id();
                String name = hotelModel.getHotel_name();
                String address = hotelModel.getHotel_address();
                String phone = hotelModel.getHotel_phone();
                String email = hotelModel.getHotel_email();
                String website = hotelModel.getHotel_website();
                String image_url = hotelModel.getHotel_image();

                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("address",address);
                intent.putExtra("phone",phone);
                intent.putExtra("email",email);
                intent.putExtra("website",website);
                intent.putExtra("image",image_url);
                intent.putExtra("user_email",user_email);

                activity.startActivity(intent);

            }
        });




    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void filteredList(ArrayList<HotelModel> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView hotel_image;
        TextView hotel_name;


        public MyViewHolder(@NonNull View itemView ) {
            super(itemView);

            hotel_image = itemView.findViewById(R.id.HotelImage);
            hotel_name = itemView.findViewById(R.id.HotelName);

        }
    }


}


