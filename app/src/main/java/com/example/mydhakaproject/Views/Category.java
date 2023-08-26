package com.example.mydhakaproject.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mydhakaproject.Adapters.CategoryAdapter;
import com.example.mydhakaproject.Models.ModelMain;
import com.example.mydhakaproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Category extends AppCompatActivity {


    RecyclerView recyclerView;

    CategoryAdapter categoryAdapter;
    ArrayList<ModelMain> list;
    int[] images;
    String[] itemName;
    String user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent2 = getIntent();
        user_email = intent2.getStringExtra("user_email");

        RecyclerOne();
        RecyclerTwo();
        RecyclerThree();
        bottomNavTask();
    }


    public void RecyclerOne() {

        list = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerOne);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        images = new int[]{R.drawable.hospital, R.drawable.ambulance, R.drawable.police, R.drawable.fire_service, R.drawable.blood_donor, R.drawable.blood_bank, R.drawable.lawyer, R.drawable.help_me};

        itemName = getResources().getStringArray(R.array.CategoryOne);


        for (int i = 0; i < images.length; i++) {
            ModelMain model = new ModelMain(images[i], itemName[i]);
            list.add(model);
        }

        categoryAdapter = new CategoryAdapter(this,Category.this, list);

        recyclerView.setAdapter(categoryAdapter);

    }

    public void RecyclerTwo() {

        list = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerTwo);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setHasFixedSize(true);

        images = new int[]{R.drawable.hotel, R.drawable.restaurant,R.drawable.parks};

        itemName = getResources().getStringArray(R.array.MainSecondString);


        for (int i = 0; i < images.length; i++) {
            ModelMain model = new ModelMain(images[i], itemName[i]);
            list.add(model);
        }

        categoryAdapter = new CategoryAdapter(this,Category.this, list);
        recyclerView.setAdapter(categoryAdapter);

    }

    public void RecyclerThree() {

        list = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerThree);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);


        images = new int[]{R.drawable.desco_icon, R.drawable.wasa_icon, R.drawable.titas_icon, R.drawable.daraz_icon,R.drawable.pathaoicon,R.drawable.foodpandaicon,
                            R.drawable.bikroy_icon,R.drawable.bdjobs_icon,R.drawable.gozayaan_icon,R.drawable.shohoz_icon,
                            R.drawable.bdrailway_icon,R.drawable.sim_icon};

        itemName = getResources().getStringArray(R.array.CategoryThree);


        for (int i = 0; i < images.length; i++) {
            ModelMain model = new ModelMain(images[i], itemName[i]);
            list.add(model);
        }

        categoryAdapter = new CategoryAdapter(this, Category.this,list);
        recyclerView.setAdapter(categoryAdapter);

    }

    public void bottomNavTask() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.category_btn);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                String Item  = item.getTitle().toString();


                if (Item.contains("Home")){

                    Intent intent = new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                    finish();

                }
                else if(Item.contains("Category"))
                {
                    Intent intent1 = new Intent(getApplicationContext(), Category.class);
                    intent1.putExtra("user_email",user_email);
                    startActivity(intent1);
                }
                else if (Item.contains("Notification"))
                {

                    Toast.makeText(getApplicationContext(), "Hello I am Notification", Toast.LENGTH_SHORT).show();

                }
                else{


                    Toast.makeText(getApplicationContext(), "Hello I am About", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }





}