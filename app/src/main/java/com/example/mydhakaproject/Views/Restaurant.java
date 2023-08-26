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
import com.example.mydhakaproject.Adapters.RestaurantAdapter;
import com.example.mydhakaproject.Models.RestaurantModel;
import com.example.mydhakaproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends AppCompatActivity {
    List<RestaurantModel> list;
    RecyclerView recyclerView;

    RestaurantAdapter restaurantAdapter;
    ProgressBar progressBar;
    SearchView searchView;

    TextView recycler_title;
    String user_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        list = new ArrayList<>();

        Intent intent2 = getIntent();
        user_email = intent2.getStringExtra("user_email");


        progressBar = findViewById(R.id.restaurant__progress_bar);
        recyclerView = findViewById(R.id.RestaurantRecyclerView);
        searchView = findViewById(R.id.restaurant_search_view);
        recycler_title = findViewById(R.id.restaurant_recycler_title);

        getData();
        ImageSlider();
        filteredItem();
        bottomNavTask();
    }

    public void getData() {


        String url = "https://mydhaka.000webhostapp.com/My%20Dhaka/restaurant.json";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                list.clear();
                progressBar.setVisibility(View.GONE);
                recycler_title.setText("Restaurant:");


                try {
                    JSONArray jsonArray = response.getJSONArray("Restaurant");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String name = jsonObject.getString("name");
                        String description = jsonObject.getString("description");
                        String focus = jsonObject.getString("focus");
                        String address = jsonObject.getString("address");
                        String phone = jsonObject.getString("phone");
                        String website = jsonObject.getString("website");
                        String image_url = jsonObject.getString("image");

                        RestaurantModel restaurantModel = new RestaurantModel();

                        restaurantModel.setName(name);
                        restaurantModel.setDescription(description);
                        restaurantModel.setFocus(focus);
                        restaurantModel.setWebsite(website);
                        restaurantModel.setAddress(address);
                        restaurantModel.setPhone(phone);
                        restaurantModel.setImage_url(image_url);


                        list.add(restaurantModel);

                    }


                } catch (JSONException e) {

                    Toast.makeText(getApplicationContext(),"Please Wait...", Toast.LENGTH_SHORT).show();
                }

                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setHasFixedSize(true);
                restaurantAdapter = new RestaurantAdapter(getApplicationContext(), Restaurant.this, list);
                recyclerView.setAdapter(restaurantAdapter);

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
                ArrayList<RestaurantModel> searchList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName().toLowerCase().contains(newText.toLowerCase())) {
                        searchList.add(list.get(i));
                        restaurantAdapter.filteredList(searchList);
                    }
                }

                return true;
            }
        });

    }


    public void ImageSlider() {

        ArrayList<SlideModel> ItemList = new ArrayList<>();

        ImageSlider imageSlider = findViewById(R.id.restaurant_image_slider);

        ItemList.add(new SlideModel(R.drawable.restaurantone, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.restauranttwo, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.restaurantthree, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.restaurantfour, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.restaurantfive, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.restaurantsix, ScaleTypes.FIT));


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



