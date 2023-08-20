package com.example.mydhakaproject.Adapters;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydhakaproject.Models.ModelMain;
import com.example.mydhakaproject.R;
import com.example.mydhakaproject.Views.Ambulance;
import com.example.mydhakaproject.Views.BloodBank;
import com.example.mydhakaproject.Views.BloodDonor;
import com.example.mydhakaproject.Views.Category;
import com.example.mydhakaproject.Views.FireService;
import com.example.mydhakaproject.Views.HelpMe;
import com.example.mydhakaproject.Views.Hospital;
import com.example.mydhakaproject.Views.Hotel;
import com.example.mydhakaproject.Views.Lawyer;
import com.example.mydhakaproject.Views.Police;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {


    Context context;
    Activity activity;
    ArrayList<ModelMain> list;


    public CategoryAdapter(Context context,Activity activity, ArrayList<ModelMain> list) {
        this.context = context;
        this.activity = activity;
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

                } else if (text.contains("Blood Donor")) {

                    context.startActivity(new Intent(context, BloodDonor.class));
                } else if (text.contains("Blood Bank")) {

                    context.startActivity(new Intent(context, BloodBank.class));
                } else if (text.contains("Lawyer")) {

                    context.startActivity(new Intent(context, Lawyer.class));
                } else if (text.contains("Help Me")) {

                    context.startActivity(new Intent(context, HelpMe.class));
                }
                else if (text.contains("Hotel")) {

                    Intent intent = activity.getIntent();
                    String user_email = intent.getStringExtra("user_email");

                    Intent intent1 = new Intent(context, Hotel.class);
                    intent1.putExtra("user_email",user_email);
                    activity.startActivity(intent1);
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

