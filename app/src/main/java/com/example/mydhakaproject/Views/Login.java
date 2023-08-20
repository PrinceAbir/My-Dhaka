package com.example.mydhakaproject.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mydhakaproject.R;

public class Login extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button btnLogin;
    ProgressBar progressBar;
    TextView signUplink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.Login_progress_bar);
        signUplink = findViewById(R.id.signUpLink);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Please insert email and password", Toast.LENGTH_SHORT).show();
                } else if (!email.contains("@")) {
                    editEmail.setError("Email is Invalid");

                }
                else{
                    login(email,password);
                }


            }
        });

        signUplink.setOnClickListener( v-> {
            startActivity(new Intent(getApplicationContext(),SignUp.class));
            finish();
        });
    }

    public void login(String email, String password) {
        progressBar.setVisibility(View.VISIBLE);


        String url = "https://mydhaka.000webhostapp.com/My%20Dhaka/login.php?email=" + email + "&password=" + password;

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                Log.d("valueofdata",response);

                if (response.equalsIgnoreCase("success")){
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(Login.this,Home.class);
                    intent.putExtra("user_email",email);
                    startActivity(intent);
                }
                else if (response.equalsIgnoreCase("failed")){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "user Not Found", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Login.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });


        requestQueue.add(stringRequest);

    }


}
