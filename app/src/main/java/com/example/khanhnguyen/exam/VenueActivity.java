package com.example.khanhnguyen.exam;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VenueActivity extends AppCompatActivity implements GetVenuesAsyncTask.IData{
    String City,currentDate;
    String baseURL = "https://api.foursquare.com/v2/venues/search?"+
            "client_id=A3QRDKM5RG4SC2R2Y4SZEC1CD05L4PUQCUF4XO0Q34VXEIUM"+
            "&client_secret=PWRQFUW0PGZYHGNSRQEISTPFXKOF4WHFZUD3HOHXT5QHY4ET&v=999&"+
            "near=CITY,STATE";

    ArrayList<Venue> venuesList;
    ProgressDialog pd;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        pd = new ProgressDialog(this);
        venuesList = new ArrayList<Venue>();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Venue Activity");

        if(getIntent().getExtras()!=null && getIntent().getExtras().containsKey("CITY")){
            Log.d("venu act",getIntent().getStringExtra("CITY"));
            City = getIntent().getStringExtra("CITY").replace(" ", "_");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            currentDate = sdf.format(new Date());
            Log.d("date",currentDate);
            baseURL = baseURL.replace("CITY,STATE", City);
            baseURL = baseURL.replace("999",currentDate);
            Log.d("url", baseURL);
            new GetVenuesAsyncTask(this).execute(baseURL);
        }

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public ProgressDialog getProgressDialogue() {
        pd.setCancelable(false);
        pd.setTitle("Loading Venues");
        return pd;
    }

    @Override
    public ListView getListView() {
        return (ListView) findViewById(R.id.listView);
    }

    @Override
    public void setVenueList(ArrayList<Venue> venueList) {
        if(venueList!=null && !venueList.isEmpty())
            venuesList = venueList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_view:
                Log.d("action","view");
                break;
            case R.id.action_clear:
                Log.d("action","clear");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
