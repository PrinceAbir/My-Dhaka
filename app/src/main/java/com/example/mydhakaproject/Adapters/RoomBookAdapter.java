package com.example.mydhakaproject.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.mydhakaproject.Models.RoomModel;
import com.example.mydhakaproject.R;

import java.util.List;


public class RoomBookAdapter extends RecyclerView.Adapter<RoomBookAdapter.MyViewHolder> {


    Context context;
    List<RoomModel> list;
    Activity activity;


    public RoomBookAdapter(Context context, Activity activity, List<RoomModel> itemList) {
        this.context = context;
        this.activity = activity;
        this.list = itemList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_roomview, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getHotel_Image()).into(holder.hotel_image);

        holder.hotel_name.setText(list.get(position).getHotelName());
        holder.roomNumber.setText("Room Number: " + list.get(position).getRoomNumber());
        holder.roomType.setText("Room Type: " + list.get(position).getRoomType());
        holder.bedCount.setText("Bed: " + list.get(position).getBedCount());
        holder.price.setText("Price: " + list.get(position).getPrice() + " (1 day)");
        holder.bookNow.setText("Book Now");

        Intent intent = activity.getIntent();
        String user_email = intent.getStringExtra("user_email");

        String hotel_id = String.valueOf(list.get(position).getHotel_ID());

        holder.bookNow.setOnClickListener(v -> {

            String url = "https://mydhaka.000webhostapp.com/My%20Dhaka/bookroom.php?user_email=" + user_email + "&hotel_name=" + list.get(position).getHotelName() + "&hotel_id=" + list.get(position).getHotel_ID() + "&room_number=" + list.get(position).getRoomNumber() + "&room_type=" + list.get(position).getRoomType() + "&bed_count=" + list.get(position).getBedCount() + "&price=" + list.get(position).getPrice();


            RequestQueue requestQueue = Volley.newRequestQueue(context);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Success")) {

                        Toast.makeText(context, "Room Booking Request has been sent Successfully.", Toast.LENGTH_SHORT).show();

//                        String recipientEmail = user_email;
//                        String subject = "Room Booking Confirmation";
//                        String message = "Dear user,\n\nYour booking has been confirmed.\n\nHotel Name: " + list.get(holder.getAdapterPosition()).getHotelName() + "\nRoom Number: " + list.get(holder.getAdapterPosition()).getRoomNumber() + "\nRoom Type: " + list.get(holder.getAdapterPosition()).getRoomNumber() + "\nBed: " + list.get(holder.getAdapterPosition()).getBedCount() + "\nPrice: " + list.get(holder.getAdapterPosition()).getPrice() + "\n\n\nThank you for choosing our service!";
//
//                        Intent intent = new Intent(Intent.ACTION_SENDTO);
//                        intent.setData(Uri.parse("mailto:")); // Only email apps should handle this
//                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipientEmail});
//                        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//                        intent.putExtra(Intent.EXTRA_TEXT, message);
//
//                        if (intent.resolveActivity(activity.getPackageManager()) != null) {
//                            activity.startActivity(intent);
//                        }

                    } else if (response.equalsIgnoreCase("Failed")) {
                        Toast.makeText(context, "Sorry. Room Booking Request Failed!", Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(stringRequest);

        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView hotel_image;
        TextView hotel_name, roomNumber, roomType, bedCount, price;
        Button bookNow;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            hotel_image = itemView.findViewById(R.id.HOTEL_IMAGE);
            hotel_name = itemView.findViewById(R.id.HOTEL_NAME);
            roomNumber = itemView.findViewById(R.id.room_number);
            roomType = itemView.findViewById(R.id.room_type);
            bedCount = itemView.findViewById(R.id.bedCount);
            price = itemView.findViewById(R.id.price);
            bookNow = itemView.findViewById(R.id.book_button);

        }
    }


}



