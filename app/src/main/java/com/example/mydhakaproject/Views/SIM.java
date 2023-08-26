package com.example.mydhakaproject.Views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.example.mydhakaproject.Adapters.FireServiceAdapter;
import com.example.mydhakaproject.Adapters.SIMAdapter;
import com.example.mydhakaproject.Models.Model;
import com.example.mydhakaproject.Models.SimModel;
import com.example.mydhakaproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SIM extends AppCompatActivity {
    List<SimModel> list;
    RecyclerView recyclerView;
    SIMAdapter simAdapter;

    TextView recycler_title;

    Button robiButton, GrameenPhoneButton, AirtelButton,TeletalkButton, BanglalinkButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim);

        list = new ArrayList<>();


        recyclerView = findViewById(R.id.SIMRecyclerView);
        recycler_title = findViewById(R.id.SIM_Operator);
        robiButton = findViewById(R.id.robiButton);
        GrameenPhoneButton = findViewById(R.id.grameenPhoneButton);
        AirtelButton = findViewById(R.id.airtelButton);
        TeletalkButton = findViewById(R.id.teletalkButton);
        BanglalinkButton = findViewById(R.id.banglalinkButton);



        robiButton.setOnClickListener( v-> {
            getData(0);
        });

        GrameenPhoneButton.setOnClickListener( v-> {
            getData(1);
        });

        AirtelButton.setOnClickListener( v-> {
            getData(2);
        });

        TeletalkButton.setOnClickListener( v-> {
            getData(3);
        });

        BanglalinkButton.setOnClickListener( v-> {
            getData(4);
        });


        bottomNavTask();


    }

    public void getData(int index) {


        String url = "https://mydhaka.000webhostapp.com/My%20Dhaka/SIM.json";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                list.clear();

                try {
                    JSONArray jsonArray = response.getJSONArray("operator");

                    JSONObject jsonObject = (JSONObject) jsonArray.get(index);

                    String operatorName = jsonObject.getString("name");

                    JSONArray jsonArray1 = jsonObject.getJSONArray("codes");

                    for (int i = 0; i < jsonArray1.length(); i++) {

                        JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                        String title = jsonObject1.getString("title");
                        String sub_title = jsonObject1.getString("sub-title");
                        String code = jsonObject1.getString("code");

                        SimModel simModel = new SimModel();
                        simModel.setOperator(operatorName);
                        simModel.setTitle(title);
                        simModel.setSubTitle(sub_title);
                        simModel.setCode(code);

                        list.add(simModel);
                    }

                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                    recyclerView.setHasFixedSize(true);
                    simAdapter = new SIMAdapter(getApplicationContext(), SIM.this, list);
                    recyclerView.setAdapter(simAdapter);




                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Please wait..", Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                recycler_title.setText("");
            }
        });

        requestQueue.add(jsonObjectRequest);


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



