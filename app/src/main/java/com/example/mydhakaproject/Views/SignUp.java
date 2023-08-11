package com.example.mydhakaproject.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mydhakaproject.Adapters.FireServiceAdapter;
import com.example.mydhakaproject.Models.Model;
import com.example.mydhakaproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity {

    private EditText editUsername, editEmail, editPassword, editPhoneNumber, editAddress;
    private Button btnSignUp;
    ProgressBar progressBar;
    TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editUsername = findViewById(R.id.editUsername);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editPhoneNumber = findViewById(R.id.editPhoneNumber);
        editAddress = findViewById(R.id.editAddress);
        btnSignUp = findViewById(R.id.btnSignUp);
        progressBar = findViewById(R.id.signUp_progress_bar);
        loginLink = findViewById(R.id.login_link);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editUsername.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String phoneNumber = editPhoneNumber.getText().toString();
                String address = editAddress.getText().toString();

                if (username.isEmpty()) {
                    editUsername.setError("Name cannot be empty!");
                } else if (!email.contains("@")) {
                    editEmail.setError("Email is Invalid");
                } else if (password.length() < 6) {
                    editPassword.setError("Password must be greater than 6 character");
                } else if (phoneNumber.length() < 11 || phoneNumber.length() > 14) {
                    editPhoneNumber.setError("Number is invalid");
                } else if (address.isEmpty()) {
                    editAddress.setError("Address cannot be empty!");
                } else {

                    insertData(username, email, password, phoneNumber, address);
                }
            }
        });

        loginLink.setOnClickListener( v->{
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        });
    }

    public void insertData(String username, String email, String password, String phoneNumber, String address) {
        progressBar.setVisibility(View.VISIBLE);

        String url = "https://mydhaka.000webhostapp.com/My%20Dhaka/data.php?username=" + username + "&email=" + email + "&password=" + password + "&phone_number=" + phoneNumber + "&address=" + address;

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               progressBar.setVisibility(View.GONE);
               Toast.makeText(SignUp.this,"Registration Successful", Toast.LENGTH_SHORT).show();

               editUsername.setText("");
               editEmail.setText("");
               editPassword.setText("");
               editPhoneNumber.setText("");
               editAddress.setText("");

                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SignUp.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });


        requestQueue.add(stringRequest);

    }


}
