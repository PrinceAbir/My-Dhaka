package com.example.mydhakaproject.Views;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mydhakaproject.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HelpMe extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    private static final int REQUEST_SMS_PERMISSION = 200;
    FusedLocationProviderClient fusedLocationProviderClient;
    String country, city, address, longitude, latitude,map;
    Button helpMeButton, saveMessageButton;
    TextView textView;
    FloatingActionButton addButton;
    EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_me);

        textView = findViewById(R.id.textview);
        helpMeButton = findViewById(R.id.getLocation);

        addButton = findViewById(R.id.addButton);

        messageEditText = findViewById(R.id.messageEditText);
        saveMessageButton = findViewById(R.id.messageSaveButton);

        bottomNavTask();


        SharedPreferences sharedpref = getSharedPreferences("Message", MODE_PRIVATE);
        String msg = sharedpref.getString("message", "");
        if (!msg.isEmpty()) {
            messageEditText.setText(msg);
        }

        saveMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emergencyMessageSave();
            }
        });


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        helpMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
                SMSPermission();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addNumberAlert();
            }
        });
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Geocoder geocoder = new Geocoder(HelpMe.this, Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    latitude = String.valueOf((addresses.get(0).getLatitude()));
                                    longitude = String.valueOf((addresses.get(0).getLongitude()));
                                    map = "Google Map Link: "+latitude +","+ longitude;
                                    address = ("Address :" + addresses.get(0).getAddressLine(0));
                                    city = ("City :" + addresses.get(0).getLocality());
                                    country = ("Country :" + addresses.get(0).getCountryName());


                                    SharedPreferences sharedpref1 = getSharedPreferences("Message", MODE_PRIVATE);
                                    String msg = sharedpref1.getString("message", "");

                                    SharedPreferences sharedpref2 = getSharedPreferences("Data", MODE_PRIVATE);

                                    String num1 = sharedpref2.getString("person1", "");
                                    String num2 = sharedpref2.getString("person2", "");
                                    String num3 = sharedpref2.getString("person3", "");


                                    if (!msg.isEmpty()) {
                                        String Message = msg + "\n"+ map+ "\n" + address + "\n" + city + "\n" + country + "\n\n";
                                        if (!num1.isEmpty()) {
                                            Toast.makeText(HelpMe.this, Message+ " ", Toast.LENGTH_SHORT).show();
                                            SendSMS(num1, Message);
                                            if (!num2.isEmpty()) {
                                                SendSMS(num2, Message);
                                                if (!num3.isEmpty()) {
                                                    SendSMS(num3, Message);
                                                }
                                            }

                                        }
                                    } else {
                                        Toast.makeText(HelpMe.this, "Please put a Emergency Message First!", Toast.LENGTH_SHORT).show();
                                    }


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }

                        }
                    });


        } else {

            askPermission();

        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(HelpMe.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    private void SMSPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS_PERMISSION);
        } else {

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == REQUEST_SMS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(this, "Please Allow the SMS Permission to SEND SMS", Toast.LENGTH_SHORT).show();
            }


            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


    public void addNumberAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(LayoutInflater.from(this).inflate(R.layout.custom_addnumber_dialog, null));
        builder.setCancelable(false);


        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialog.show();


        EditText tv1, tv2;

        tv1 = alertDialog.findViewById(R.id.name);
        tv2 = alertDialog.findViewById(R.id.number);
        Button saveButton = alertDialog.findViewById(R.id.saveButton);
        Button cancelButton = alertDialog.findViewById(R.id.cancelButton);

        saveButton.setOnClickListener(v -> {

            String name = tv1.getText().toString().trim();
            String number = tv2.getText().toString().trim();

            if (!name.isEmpty() || !number.isEmpty()) {
                SharedPreferences sharedpref = getSharedPreferences("Data", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedpref.edit();
                if (sharedpref.getString("person1", "").isEmpty()) {
                    edit.putString("person1", number);
                    Toast.makeText(this, "Contact Saved Successfully", Toast.LENGTH_SHORT).show();
                } else if (sharedpref.getString("person2", "").isEmpty()) {
                    edit.putString("person2", number);
                    Toast.makeText(this, "Contact Saved Successfully", Toast.LENGTH_SHORT).show();
                } else if (sharedpref.getString("person3", "").isEmpty()) {

                    edit.putString("person3", number);
                    Toast.makeText(this, "Contact Saved Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Maximum Number Inserted", Toast.LENGTH_SHORT).show();
                }
                edit.apply();
                alertDialog.dismiss();


            } else {
                Toast.makeText(this, "Name/Mobile number cannot be empty!", Toast.LENGTH_SHORT).show();
            }

        });
        cancelButton.setOnClickListener(v ->

                alertDialog.dismiss()

        );

        alertDialog.setCancelable(true);


    }


    public void emergencyMessageSave() {
        String msg = messageEditText.getText().toString();
        if (!msg.isEmpty()) {
            SharedPreferences sharedpref = getSharedPreferences("Message", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedpref.edit();
            edit.putString("message", msg);
            edit.apply();
            Toast.makeText(this, "Message Saved Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please put a Emergency Message First!", Toast.LENGTH_SHORT).show();
        }
    }

    public void bottomNavTask() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.category_btn);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                String Item = item.getTitle().toString();


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


    private void SendSMS(String number, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, msg, null, null);
            Toast.makeText(this, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "SMS sending failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
