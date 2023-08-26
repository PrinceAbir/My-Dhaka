package com.example.mydhakaproject.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mydhakaproject.Adapters.HotelAdapter;
import com.example.mydhakaproject.Adapters.RoomBookAdapter;
import com.example.mydhakaproject.Models.HotelModel;
import com.example.mydhakaproject.Models.RoomModel;
import com.example.mydhakaproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookRoom extends AppCompatActivity {

    int id;
    String name,address,phone,email,website,image_url;
    RecyclerView recyclerView;

    List<RoomModel> list;

    RoomBookAdapter roomBookAdapter;
    String user_email;

    Button bookNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_room);


        Intent intent = getIntent();

        recyclerView = findViewById(R.id.RoomRecyclerView);



        id = intent.getIntExtra("id",0);
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        phone = intent.getStringExtra("phone");
        email = intent.getStringExtra("email");
        website = intent.getStringExtra("website");
        image_url = intent.getStringExtra("image");
        user_email = intent.getStringExtra("user_email");


        getData();
    }

    public void getData() {


        String url = "https://mydhaka.000webhostapp.com/My%20Dhaka/viewroom.php?hotel_id="+id;

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                list = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        String id = jsonObject.getString("id");
                        String hotel_id = jsonObject.getString("hotel_id");
                        String roomNumber = jsonObject.getString("room_number");
                        String roomType = jsonObject.getString("room_type");
                        String bedCount = jsonObject.getString("bed_count");
                        String Price = jsonObject.getString("price");
                        String Status = jsonObject.getString("status");
                        String image = jsonObject.getString("image");

                        RoomModel roomModel = new RoomModel();



                        roomModel.setHotelName(name);
                        roomModel.setHotel_ID(Integer.parseInt(hotel_id));
                        roomModel.setHotel_Image(image);
                        roomModel.setRoomNumber(roomNumber);
                        roomModel.setRoomType(roomType);
                        roomModel.setBedCount(bedCount);
                        roomModel.setPrice(Price);


                        list.add(roomModel);


                    } catch (JSONException e) {

                        Toast.makeText(BookRoom.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                       //Toast.makeText(getApplicationContext(), "Please wait..", Toast.LENGTH_SHORT).show();
                    }

                }

                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(BookRoom.this,LinearLayoutManager.VERTICAL,false));
                recyclerView.setHasFixedSize(true);
                roomBookAdapter = new RoomBookAdapter(getApplicationContext(),BookRoom.this, list);
                recyclerView.setAdapter(roomBookAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Please Check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });



        requestQueue.add(jsonArrayRequest);

    }
}