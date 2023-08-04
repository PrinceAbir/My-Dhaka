package com.example.mydhakaproject.Adapters;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mydhakaproject.Models.Model;
import com.example.mydhakaproject.R;

import java.util.ArrayList;
import java.util.List;


public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.MyViewHolder> {


    Context context;
    Activity activity;
    List<Model> list;


    public HospitalAdapter(Context context, Activity activity, List<Model> itemList) {
        this.context = context;
        this.activity = activity;
        this.list = itemList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_item_design, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(list.get(position).getName());
        holder.category.setText(list.get(position).getCategory());
        holder.address.setText(list.get(position).getAddress());
        holder.phone1.setText(list.get(position).getPhone1());
        holder.phone2.setText(list.get(position).getPhone2());

        holder.lottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = list.get(holder.getAdapterPosition()).getPhone1();

                if (!phoneNumber.isEmpty()) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    activity.startActivity(intent);

                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void filteredList(ArrayList<Model> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, category, address, phone1, phone2;
        LottieAnimationView lottie;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            category = itemView.findViewById(R.id.category);
            address = itemView.findViewById(R.id.address);
            phone1 = itemView.findViewById(R.id.phone1);
            phone2 = itemView.findViewById(R.id.phone2);
            lottie = itemView.findViewById(R.id.callButton);

        }
    }


}

