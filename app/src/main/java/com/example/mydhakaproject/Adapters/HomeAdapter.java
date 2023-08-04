package com.example.mydhakaproject.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydhakaproject.Models.ModelMain;
import com.example.mydhakaproject.R;

import java.util.ArrayList;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


    Context context;
    ArrayList<ModelMain> list;


    public HomeAdapter(Context context, ArrayList<ModelMain> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_view, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView.setText(list.get(position).getName());
        holder.imageView.setImageResource(list.get(position).getImageLink());

        holder.itemView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.move_out));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = list.get(holder.getAdapterPosition()).getName();

                if (text.contains("Hospital")) {

                    context.startActivity(new Intent(context, Hospital.class));
                } else if (text.contains("Ambulance")) {

                    context.startActivity(new Intent(context, Ambulance.class));
                } else if (text.contains("Police")) {

                    context.startActivity(new Intent(context, Police.class));

                } else if (text.contains("Fire Service")) {

                    context.startActivity(new Intent(context, FireService.class));
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.sampleImageView);
            textView = itemView.findViewById(R.id.sampleTextView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}

