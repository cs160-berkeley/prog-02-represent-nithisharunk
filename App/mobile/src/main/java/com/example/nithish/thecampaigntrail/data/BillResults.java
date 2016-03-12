package com.example.nithish.thecampaigntrail.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nithish on 3/9/16.
 */
public class BillResults implements JSONpopulater {

    private JSONObject bill1;
    private JSONObject bill2;
    private JSONObject bill3;
    private String billName1;
    private String billName2;
    private String billName3;

    public JSONObject getBill1() {
        return bill1;
    }

    public JSONObject getBill2() {
        return bill2;
    }

    public JSONObject getBill3() {
        return bill3;
    }

    public String getBillName1() {
        return billName1;
    }

    public String getBillName2() {
        return billName2;
    }

    public String getBillName3() {
        return billName3;
    }

    @Override
    public void populate(JSONArray data) {

        try {
            bill1 = data.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            bill2 = data.getJSONObject(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            bill3 = data.getJSONObject(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        billName1 = bill1.optString("short_title");
        if(billName1 == "null"){billName1 = bill1.optString("official_title");}
        billName2 = bill2.optString("short_title");
        if(billName2 == "null"){billName2 = bill2.optString("official_title");}
        billName3 = bill3.optString("short_title");
        if(billName3 == "null"){billName3 = bill3.optString("official_title");}



    }
}
