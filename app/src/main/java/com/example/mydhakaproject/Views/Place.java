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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mydhakaproject.Adapters.PlaceAdapter;
import com.example.mydhakaproject.Adapters.RestaurantAdapter;
import com.example.mydhakaproject.Models.PlaceModel;
import com.example.mydhakaproject.Models.RestaurantModel;
import com.example.mydhakaproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Place extends AppCompatActivity {
    List<PlaceModel> list;
    RecyclerView recyclerView;

    PlaceAdapter placeAdapter;
    ProgressBar progressBar;
    SearchView searchView;

    TextView recycler_title;
    String user_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        list = new ArrayList<>();

        Intent intent2 = getIntent();
        user_email = intent2.getStringExtra("user_email");


        progressBar = findViewById(R.id.place_progress_bar);
        recyclerView = findViewById(R.id.PlaceRecyclerView);
        searchView = findViewById(R.id.place_search_view);
        recycler_title = findViewById(R.id.place_recycler_title);

        getData();
        ImageSlider();
        filteredItem();
        bottomNavTask();
    }

    public void getData() {


        String url = "https://mydhaka.000webhostapp.com/My%20Dhaka/place.json";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                list.clear();
                progressBar.setVisibility(View.GONE);
                recycler_title.setText("Tourist Spots:");


                try {
                    JSONArray jsonArray = response.getJSONArray("tourist_spots");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String name = jsonObject.getString("name");
                        String image_url = jsonObject.getString("image");
                        String about = jsonObject.getString("about");
                        String google_map = jsonObject.getString("google_map");

                        PlaceModel placeModel = new PlaceModel();

                        placeModel.setName(name);
                        placeModel.setImage(image_url);
                        placeModel.setAbout(about);
                        placeModel.setGoogleMap(google_map);


                        list.add(placeModel);

                    }


                } catch (JSONException e) {

                    Toast.makeText(getApplicationContext(),"Please Wait...", Toast.LENGTH_SHORT).show();
                }

                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setHasFixedSize(true);
                placeAdapter = new PlaceAdapter(getApplicationContext(), Place.this, list);
                recyclerView.setAdapter(placeAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Please Check Your Internet Connection!", Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(jsonObjectRequest);

    }

    public void filteredItem() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<PlaceModel> searchList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName().toLowerCase().contains(newText.toLowerCase())) {
                        searchList.add(list.get(i));
                        placeAdapter.filteredList(searchList);
                    }
                }

                return true;
            }
        });

    }


    public void ImageSlider() {

        ArrayList<SlideModel> ItemList = new ArrayList<>();

        ImageSlider imageSlider = findViewById(R.id.place_image_slider);

        ItemList.add(new SlideModel(R.drawable.restaurantone, ScaleTypes.FIT));


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



