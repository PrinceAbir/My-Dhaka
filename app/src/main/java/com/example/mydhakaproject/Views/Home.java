package com.example.mydhakaproject.Views;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mydhakaproject.Adapters.HomeAdapter;
import com.example.mydhakaproject.Models.ModelMain;
import com.example.mydhakaproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;

    HomeAdapter homeAdapter;
    ArrayList<ModelMain> list;

    String[] name;

    LottieAnimationView callButton;
    String user_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent2 = getIntent();
        user_email = intent2.getStringExtra("user_email");

        RecyclerPartOne();
        RecyclerPartTwo();
        RecyclerPartThree();
        bottomNavTask();
        ImageSlider();


        callButton = findViewById(R.id.callButton);

        callButton.setOnClickListener(v -> {

            String phoneNumber = "999";

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);

        });


    }


    public void RecyclerPartOne() {


        recyclerView = findViewById(R.id.MainFirstRecyclerView);

        list = new ArrayList<>();


        int[] imageID = {R.drawable.hospital, R.drawable.ambulance, R.drawable.police, R.drawable.fire_service};

        name = getResources().getStringArray(R.array.MainPartString);


        for (int i = 0; i < imageID.length; i++) {
            ModelMain model = new ModelMain(imageID[i], name[i]);
            list.add(model);
        }


        homeAdapter = new HomeAdapter(this,Home.this,  list);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);


        Button button = findViewById(R.id.emergency_More);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Category.class);
                intent.putExtra("user_email",user_email);
                startActivity(intent);
            }
        });

    }

    public void RecyclerPartTwo() {


        recyclerView = findViewById(R.id.MainSecondRecyclerView);

        list = new ArrayList<>();


        int[] imageID = {R.drawable.hotel, R.drawable.restaurant, R.drawable.shopping_mall};

        name = getResources().getStringArray(R.array.MainSecondString);


        for (int i = 0; i < imageID.length; i++) {
            ModelMain model = new ModelMain(imageID[i], name[i]);
            list.add(model);
        }


        homeAdapter = new HomeAdapter(this,Home.this, list);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);


        Button button = findViewById(R.id.other_more);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Category.class);
                intent.putExtra("user_email",user_email);
                startActivity(intent);
            }
        });
    }

    public void RecyclerPartThree() {

        recyclerView = findViewById(R.id.MainThirdRecyclerView);

        list = new ArrayList<>();


        int[] imageID = {R.drawable.desco_icon, R.drawable.wasa_icon, R.drawable.titas_icon, R.drawable.daraz_icon};

        name = getResources().getStringArray(R.array.MainThirdString);


        for (int i = 0; i < imageID.length; i++) {
            ModelMain model = new ModelMain(imageID[i], name[i]);
            list.add(model);
        }


        homeAdapter = new HomeAdapter(this,Home.this,  list);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);


        Button button = findViewById(R.id.utility_more);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Category.class);
                intent.putExtra("user_email",user_email);
                startActivity(intent);
            }
        });

    }


    public void bottomNavTask() {

        bottomNavigationView = findViewById(R.id.bottom_nav);


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
                    intent.putExtra("user_email",user_email);
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


    public void showExitDialog() {

//        TextView exit, cancel;
//
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        View view = LayoutInflater.from(this).inflate(R.layout.custom_alertdialog_exit_app, null);
//        builder.setView(view);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//        alertDialog.show();
//
//
//        exit = alertDialog.findViewById(R.id.exit);
//        cancel = alertDialog.findViewById(R.id.cancel);
//
//
//        exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.super.onBackPressed();
//            }
//        });
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//

    }

    @Override
    public void onBackPressed() {

        showExitDialog();
    }

    public void ImageSlider() {

        ArrayList<SlideModel> ItemList = new ArrayList<>();

        ImageSlider imageSlider = findViewById(R.id.image_slider);

        ItemList.add(new SlideModel(R.drawable.dhaka1, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.dhaka2, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.dhaka4, ScaleTypes.FIT));
        ItemList.add(new SlideModel(R.drawable.dhaka5, ScaleTypes.FIT));

        imageSlider.setImageList(ItemList);


    }

}

