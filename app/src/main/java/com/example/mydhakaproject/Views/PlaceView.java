package com.example.mydhakaproject.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mydhakaproject.R;

public class PlaceView extends AppCompatActivity {

    ImageView place_image;
    TextView place_details, place_name, place_address;
    Button google_mapButton;
    String user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_view);


        place_name = findViewById(R.id.place_name);
        place_image = findViewById(R.id.place_image);
        place_details = findViewById(R.id.place_details);
        place_address = findViewById(R.id.place_address);


        google_mapButton = findViewById(R.id.googleMapButton);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String image_url = intent.getStringExtra("image");
        String about = intent.getStringExtra("about");
        String google_map = intent.getStringExtra("google_map");
        user_email = intent.getStringExtra("user_email");


        place_name.setText(name);
        place_details.setText(about);
        Glide.with(this).load(image_url).into(place_image);


        google_mapButton.setOnClickListener(v -> {

//            Intent intent1 = new Intent(this,WebsiteView.class);
//            intent1.putExtra("link",google_map);
//            startActivity(intent1);

        });


    }
}