package com.example.khanhnguyen.exam;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetVenuesAsyncTask extends AsyncTask<String, Void, ArrayList<Venue>> {
    IData activity;

    @Override
    protected ArrayList<Venue> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            Log.d("URL", "url" + params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int StatusCode = con.getResponseCode();
            if(StatusCode == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();

                while(line!=null){
                    builder.append(line);
                    line = reader.readLine();
                }
                return VenueListUtil.VenuesJSONParser.parseVenues(builder.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.getProgressDialogue().show();
    }

    @Override
    protected void onPostExecute(ArrayList<Venue> venues) {
        super.onPostExecute(venues);
        activity.getProgressDialogue().dismiss();
        if(venues!=null && !venues.isEmpty())
            activity.setVenueList(venues);
        ListView lv = activity.getListView();
        ArrayAdapter<String> temp = new ArrayAdapter<String>(activity.getContext(),android.R.layout.simple_list_item_1);
        for(int i=0; i<venues.size(); i++){
            temp.add(venues.get(i).getId());
        }
        lv.setAdapter(temp);
        activity.getProgressDialogue().dismiss();
    }

    public GetVenuesAsyncTask(IData activity){
        this.activity = activity;
    }

    public interface IData{
        Context getContext();
        ProgressDialog getProgressDialogue();
        ListView getListView();
        void setVenueList(ArrayList<Venue> venueList);
    }
}
