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
import com.example.mydhakaproject.Models.RestaurantModel;
import com.example.mydhakaproject.R;
import com.example.mydhakaproject.Views.RestaurantView;

import java.util.ArrayList;
import java.util.List;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {


    Context context;
    List<RestaurantModel> list;
    Activity activity;


    public RestaurantAdapter(Context context, Activity activity, List<RestaurantModel> itemList) {
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

        Glide.with(context).load(list.get(position).getImage_url()).into(holder.restaurant_Image);
        holder.restaurant_name.setText(list.get(position).getName());

        holder.itemView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.move_left));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = activity.getIntent();
                String user_email = intent2.getStringExtra("user_email");


                RestaurantModel restaurantModel = list.get(holder.getAdapterPosition());
                Intent intent = new Intent(activity, RestaurantView.class);

                String name = restaurantModel.getName();
                String description = restaurantModel.getDescription();
                String focus = restaurantModel.getFocus();
                String address = restaurantModel.getAddress();
                String phone = restaurantModel.getPhone();
                String website = restaurantModel.getWebsite();
                String image_url = restaurantModel.getImage_url();


                intent.putExtra("name", name);
                intent.putExtra("description", description);
                intent.putExtra("focus", focus);
                intent.putExtra("address", address);
                intent.putExtra("phone", phone);
                intent.putExtra("website", website);
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
    public void filteredList(ArrayList<RestaurantModel> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView restaurant_Image;
        TextView restaurant_name;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurant_Image = itemView.findViewById(R.id.HotelImage);
            restaurant_name = itemView.findViewById(R.id.HotelName);

        }
    }


}


