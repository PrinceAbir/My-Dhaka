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
import com.example.mydhakaproject.Models.PlaceModel;
import com.example.mydhakaproject.Models.RestaurantModel;
import com.example.mydhakaproject.R;
import com.example.mydhakaproject.Views.PlaceView;
import com.example.mydhakaproject.Views.RestaurantView;

import java.util.ArrayList;
import java.util.List;


public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.MyViewHolder> {


    Context context;
    List<PlaceModel> list;
    Activity activity;


    public PlaceAdapter(Context context, Activity activity, List<PlaceModel> itemList) {
        this.context = context;
        this.activity = activity;
        this.list = itemList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hotel_sample_view, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImage()).into(holder.place_image);
        holder.place_name.setText(list.get(position).getName());

        holder.itemView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.move_left));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = activity.getIntent();
                String user_email = intent2.getStringExtra("user_email");


                PlaceModel placeModel = list.get(holder.getAdapterPosition());
                Intent intent = new Intent(activity, PlaceView.class);

                String name = placeModel.getName();
                String image_url = placeModel.getImage();
                String about = placeModel.getAbout();
                String google_map = placeModel.getGoogleMap();


                intent.putExtra("name", name);
                intent.putExtra("about", about);
                intent.putExtra("google_map", google_map);
                intent.putExtra("image", image_url);
                intent.putExtra("user_email", user_email);

                activity.startActivity(intent);

            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void filteredList(ArrayList<PlaceModel> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView place_image;
        TextView place_name;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            place_image = itemView.findViewById(R.id.HotelImage);
            place_name = itemView.findViewById(R.id.HotelName);

        }
    }


}


