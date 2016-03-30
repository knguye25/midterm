package com.example.khanhnguyen.exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VenueActivity extends AppCompatActivity {
    String City,currentDate;
    String baseURL = "https://api.foursquare.com/v2/venues/search?"+
            "client_id=A3QRDKM5RG4SC2R2Y4SZEC1CD05L4PUQCUF4XO0Q34VXEIUM"+
            "&client_secret=PWRQFUW0PGZYHGNSRQEISTPFXKOF4WHFZUD3HOHXT5QHY4ET&v=999&"+
            "near=CITY,STATE";

    ArrayList<Venue> venuesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);

        venuesList = new ArrayList<Venue>();

        if(getIntent().getExtras()!=null && getIntent().getExtras().containsKey("CITY")){
            Log.d("venu act",getIntent().getStringExtra("CITY"));
            City = getIntent().getStringExtra("CITY").replace(" ", "_");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            currentDate = sdf.format(new Date());
            Log.d("date",currentDate);
            baseURL = baseURL.replace("CITY,STATE", City);
            baseURL = baseURL.replace("999",currentDate);
            Log.d("url", baseURL);
        }

    }
}
