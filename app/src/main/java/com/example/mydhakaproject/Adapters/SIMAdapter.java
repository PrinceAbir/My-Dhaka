package com.example.mydhakaproject.Adapters;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mydhakaproject.Models.Model;
import com.example.mydhakaproject.Models.SimModel;
import com.example.mydhakaproject.R;

import java.util.ArrayList;
import java.util.List;


public  class SIMAdapter extends RecyclerView.Adapter<SIMAdapter.MyViewHolder> {


    Context context;
    List<SimModel> list;
    Activity activity;



    public SIMAdapter(Context context,Activity activity, List<SimModel> itemList){
        this.context = context;
        this.activity = activity;
        this.list = itemList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_sim_view,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

      //holder.operator.setText(list.get(position).getOperator());
      holder.title.setText(list.get(position).getTitle());
      holder.sub_title.setText(list.get(position).getSubTitle());
      holder.code.setText(list.get(position).getCode());

      holder.itemView.startAnimation(AnimationUtils.loadAnimation(context,R.anim.move_left));


    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView operator,title,sub_title,code;


        public MyViewHolder(@NonNull View itemView ) {
            super(itemView);

            //operator = itemView.findViewById(R.id.SIM_Operator);
            title = itemView.findViewById(R.id.SimTitle);
            sub_title = itemView.findViewById(R.id.SimSubTitle);
            code = itemView.findViewById(R.id.SimCode);

        }
    }


}


