package com.example.khanhnguyen.exam;


import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class VenueListUtil {
    static public class VenuesJSONParser{
        static ArrayList<Venue> parseVenues(String in) throws JSONException {
            ArrayList<Venue> venuesList = new ArrayList<Venue>();
            Venue aVenue;

            JSONObject root = new JSONObject(in);
            root = root.getJSONObject("response");
            if(root.has("venues")){
                Log.d("jsonParser","has venues");
                JSONArray venuesJsonArray = root.getJSONArray("venues");
                Log.d("jsonParser", "size"+venuesJsonArray.length());


                for(int i=0; i<venuesJsonArray.length(); i++){
                    JSONObject venueJSON_Obj = venuesJsonArray.getJSONObject(i);
                    JSONObject categoriesJson = venueJSON_Obj.getJSONArray("categories").getJSONObject(0);
                    aVenue = new Venue();
                    aVenue.setId(venueJSON_Obj.getString("id"));
                    aVenue.setVenueName(venueJSON_Obj.getString("name"));
                    aVenue.setCategoryName(categoriesJson.getString("name"));
                    JSONObject imgJsonObj = categoriesJson.getJSONObject("icon");
                    String iconUrl = imgJsonObj.getString("prefix") + "bg_64" + imgJsonObj.getString("suffix");
                    aVenue.setIconUrl(iconUrl);
                    Log.d("json", iconUrl);
                    int count = venueJSON_Obj.getJSONObject("stats").getInt("checkinsCount");
                    Log.d("json", "" + count);
                    aVenue.setCheckinCounts(count);

                    if(aVenue.isComplete())
                        venuesList.add(aVenue);

                }
            }

            //JSONArray searchResultsJSONArray = root.getJSONArray("venues");
            Log.d("sizeJson", venuesList.size()+"");
            return venuesList;
        }
    }
}
