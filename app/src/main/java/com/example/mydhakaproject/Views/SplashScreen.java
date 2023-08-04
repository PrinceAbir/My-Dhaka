package com.example.mydhakaproject.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mydhakaproject.R;

public class SplashScreen extends AppCompatActivity {


    TextView appName, tagInfo;
    ImageView appIcon;
    LottieAnimationView animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        appName = findViewById(R.id.SplashAppName);
        appIcon = findViewById(R.id.SplashIcon);
        tagInfo = findViewById(R.id.tagInfo);
        animation = findViewById(R.id.lottieAnimation);


        appIcon.animate().translationXBy(-700).rotation(360).setDuration(2500).setStartDelay(0).start();
        appName.animate().translationYBy(-400).setDuration(3000).setStartDelay(0).start();
        tagInfo.animate().translationY(-300).setDuration(3500).setStartDelay(0).start();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animation.cancelAnimation();
                finish();
                startActivity(new Intent(getApplicationContext(), Home.class));

            }
        }, 6000);
    }
}