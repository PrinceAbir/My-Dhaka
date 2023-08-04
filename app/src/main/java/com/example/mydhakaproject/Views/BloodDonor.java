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
import com.example.mydhakaproject.Adapters.BloodDonorAdapter;
import com.example.mydhakaproject.Models.BloodDonorModel;
import com.example.mydhakaproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BloodDonor extends AppCompatActivity {
    List<BloodDonorModel> list;
    RecyclerView recyclerView;

    BloodDonorAdapter bloodDonorAdapter;
    ProgressBar progressBar;
    SearchView searchView;

    TextView recycler_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donor);

        list = new ArrayList<>();

        progressBar = findViewById(R.id.blood_donor_progress_bar);

        recyclerView = findViewById(R.id.BloodDonorRecyclerView);

        searchView = findViewById(R.id.blood_donor_search_view);

        recycler_title = findViewById(R.id.blood_donor_recycler_title);


        getData();
        ImageSlider();
        filteredItem();
        bottomNavTask();


    }

    public void getData() {


        String url = "https://mydhaka.000webhostapp.com/My%20Dhaka/blood_donors.json";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                list.clear();
                progressBar.setVisibility(View.GONE);
                recycler_title.setText("Blood Donors:");


                try {
                    JSONArray jsonArray = response.getJSONArray("blood_donors");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        try {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            BloodDonorModel bloodDonorModel = new BloodDonorModel();
                            bloodDonorModel.setName(jsonObject.getString("name"));
                            bloodDonorModel.setAddress(jsonObject.getString("location"));
                            bloodDonorModel.setBlood_group(jsonObject.getString("blood_group"));
                            bloodDonorModel.setPhone1(jsonObject.getString("contact_numbers1"));
                            bloodDonorModel.setPhone2(jsonObject.getString("contact_numbers2"));
                            bloodDonorModel.setCallButton(R.raw.call);
                            list.add(bloodDonorModel);


                        } catch (JSONException e) {

                            Toast.makeText(BloodDonor.this, "Please wait..", Toast.LENGTH_SHORT).show();
                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setHasFixedSize(true);
                bloodDonorAdapter = new BloodDonorAdapter(getApplicationContext(),BloodDonor.this, list);
                recyclerView.setAdapter(bloodDonorAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BloodDonor.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                recycler_title.setText("");
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
                ArrayList<BloodDonorModel> searchList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName().toLowerCase().contains(newText.toLowerCase()) || list.get(i).getAddress().toLowerCase().contains(newText.toLowerCase())) {
                        searchList.add(list.get(i));
                        bloodDonorAdapter.filteredList(searchList);
                    }
                }

                return true;
            }
        });

    }


    public void ImageSlider() {

        ArrayList<SlideModel> ItemList = new ArrayList<>();

        ImageSlider imageSlider = findViewById(R.id.blood_donor_image_slider);

        ItemList.add(new SlideModel(R.drawable.blooddonor1, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.blooddonor2, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.blooddonor3, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.blooddonor4, ScaleTypes.FIT));

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



