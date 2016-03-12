package com.example.nithish.thecampaigntrail.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nithish on 3/11/16.
 */
public class GeoResults implements JSONpopulater {
    private JSONObject results;
    private JSONArray addressComp;
    private JSONObject almostThere;
    private JSONObject stillNotQuite;
    private String county;
    private String state;

    public String getState() {
        return state;
    }

    public String getCounty() {
        return county;
    }

    @Override
    public void populate(JSONArray data) {

        try {
            results = data.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            addressComp = results.getJSONArray("address_components");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            almostThere = addressComp.getJSONObject(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            stillNotQuite = addressComp.getJSONObject(3);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        county = almostThere.optString("short_name");
        state = stillNotQuite.optString("short_name");

    }
}
