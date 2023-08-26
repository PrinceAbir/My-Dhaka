package com.example.mydhakaproject.Views;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mydhakaproject.R;

public class WebsiteView extends AppCompatActivity {

    private WebView webView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webView);



        // Enable JavaScript (optional)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Intent intent = getIntent();
        String weblink = intent.getStringExtra("link");


        // Load a website
        webView.loadUrl(weblink);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }



}