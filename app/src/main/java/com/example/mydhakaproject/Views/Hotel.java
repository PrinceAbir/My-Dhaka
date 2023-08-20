package com.example.mydhakaproject.Views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mydhakaproject.Adapters.HotelAdapter;
import com.example.mydhakaproject.Models.HotelModel;
import com.example.mydhakaproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Hotel extends AppCompatActivity {
    List<HotelModel> list;
    RecyclerView recyclerView;
    HotelAdapter hotelAdapter;
    ProgressBar progressBar;
    SearchView searchView;

    TextView recycler_title;
    String user_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        list = new ArrayList<>();

        Intent intent2 = getIntent();
        user_email = intent2.getStringExtra("user_email");


        progressBar = findViewById(R.id.hotel_progress_bar);
        recyclerView = findViewById(R.id.HotelRecyclerView);
        searchView = findViewById(R.id.hotel_search_view);
        recycler_title = findViewById(R.id.hotel_recycler_title);

        getData();
        ImageSlider();
        filteredItem();
        bottomNavTask();
    }

    public void getData() {


        String url = "https://mydhaka.000webhostapp.com/My%20Dhaka/hotel.php";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                list.clear();
                progressBar.setVisibility(View.GONE);
                recycler_title.setText("Hotels:");

                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("hotel_name");
                        String location = jsonObject.getString("location");
                        String phone = jsonObject.getString("contact_phone");
                        String email = jsonObject.getString("contact_email");
                        String website = jsonObject.getString("website");
                        String image_url = jsonObject.getString("image_url");

                        HotelModel hotelModel = new HotelModel();

                        hotelModel.setHotel_id(Integer.parseInt(id));
                        hotelModel.setHotel_name(name);
                        hotelModel.setHotel_address(location);
                        hotelModel.setHotel_phone(phone);
                        hotelModel.setHotel_email(email);
                        hotelModel.setHotel_website(website);
                        hotelModel.setHotel_image(image_url);

                        list.add(hotelModel);


                    } catch (JSONException e) {

                        Toast.makeText(getApplicationContext(), "Please wait..", Toast.LENGTH_SHORT).show();
                    }

                }

                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setHasFixedSize(true);
                hotelAdapter = new HotelAdapter(getApplicationContext(), Hotel.this, list);
                recyclerView.setAdapter(hotelAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(jsonArrayRequest);

    }

    public void filteredItem() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<HotelModel> searchList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getHotel_name().toLowerCase().contains(newText.toLowerCase())) {
                        searchList.add(list.get(i));
                        hotelAdapter.filteredList(searchList);
                    }
                }

                return true;
            }
        });

    }


    public void ImageSlider() {

        ArrayList<SlideModel> ItemList = new ArrayList<>();

        ImageSlider imageSlider = findViewById(R.id.hotel_image_slider);

        ItemList.add(new SlideModel(R.drawable.hotelone, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.hoteltwo, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.hotelthree, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.hotelfour, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.hotelfive, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.hotelsix, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.hotelseven, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.hoteleight, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.hotelnine, ScaleTypes.FIT));

        imageSlider.setImageList(ItemList);


    }

    public void bottomNavTask() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                String Item = item.getTitle().toString();
                item.getIcon().setTint(Color.RED);

                if (Item.contains("Home")) {

                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                    finish();

                } else if (Item.contains("Category")) {
                    Intent intent = new Intent(getApplicationContext(), Category.class);
                    startActivity(intent);
                    finish();
                } else if (Item.contains("Notification")) {

                    Toast.makeText(getApplicationContext(), "Hello I am Notification", Toast.LENGTH_SHORT).show();

                } else {


                    Toast.makeText(getApplicationContext(), "Hello I am About", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

}



