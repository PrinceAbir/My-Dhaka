package com.example.mydhakaproject.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mydhakaproject.R;

public class RestaurantView extends AppCompatActivity {

    ImageView restaurant_image;
    TextView restaurant_name,restaurant_details,restaurant_address;
    Button visit,callButton,emailButton;
    String user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_view);


        restaurant_name = findViewById(R.id.restaurant_name);
        restaurant_image = findViewById(R.id.restaurant_image);
        restaurant_details = findViewById(R.id.restaurant_details);
        restaurant_address = findViewById(R.id.restaurant_address);



        visit = findViewById(R.id.visit);
        callButton = findViewById(R.id.CALL_BUTTON);
        emailButton = findViewById(R.id.EMAIL_BUTTON);



        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String address = intent.getStringExtra("address");
        String phone = intent.getStringExtra("phone");
        String focus = intent.getStringExtra("focus");
        String website = intent.getStringExtra("website");
        String image_url = intent.getStringExtra("image");
        user_email = intent.getStringExtra("user_email");




        restaurant_name.setText(name);
        Glide.with(this).load(image_url).into(restaurant_image);
        restaurant_details.setText(description);
        restaurant_address.setText(address);

        visit.setOnClickListener( v-> {
            Intent intent1 = new Intent(this,WebsiteView.class);
            intent1.putExtra("link",website);
            startActivity(intent1);

        });


        callButton.setOnClickListener(v -> {

            Intent intent3 = new Intent(Intent.ACTION_DIAL);
            intent3.setData(Uri.parse("tel:" + phone));
            startActivity(intent3);

        });

        //
//        emailButton.setOnClickListener( v-> {
//
//            Intent intent2 = new Intent(Intent.ACTION_SENDTO);
//            intent2.setData(Uri.parse("mailto:" + email));
//
//            if (intent2.resolveActivity(getPackageManager()) != null) {
//                startActivity(intent2);
//            }
//        });





    }
}