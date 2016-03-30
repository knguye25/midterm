package com.example.khanhnguyen.exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Client ID: A3QRDKM5RG4SC2R2Y4SZEC1CD05L4PUQCUF4XO0Q34VXEIUM
    //Client Secret: PWRQFUW0PGZYHGNSRQEISTPFXKOF4WHFZUD3HOHXT5QHY4ET
    Spinner spinner;
    String city;
    LinearLayout linearLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.main);

        /*TOOLBAR SETUP*/
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        /*SPINNER SETUP*/
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setId(View.generateViewId());
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.cities, R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = parent.getItemAtPosition(position).toString();
                Log.d("spinner", "city = " + city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*BUTTONS ON CLICK FUNCTION*/
        findViewById(R.id.mainSubmitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(city!=null && !city.isEmpty()){
                    Intent i = new Intent(MainActivity.this, VenueActivity.class);
                    i.putExtra("CITY",city);
                    startActivity(i);
                }
            }
        });
    }

    /*TOOLBAR FUNCTIONALITY SET HERE*/
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
