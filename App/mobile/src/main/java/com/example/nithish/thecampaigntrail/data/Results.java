package com.example.nithish.thecampaigntrail.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nithish on 3/8/16.
 */
public class Results implements JSONpopulater {

    private String bioguide1;
    private String bioguide2;
    private String bioguide3;
    private String firstName1;
    private String firstName2;
    private String firstName3;
    private String lastName1;
    private String lastName2;
    private String lastName3;
    private String date1;
    private String date2;
    private String date3;
    private String partyText1;
    private String partyText2;
    private String partyText3;
    private String email1;
    private String email2;
    private String email3;
    private String web1;
    private String web2;
    private String web3;
    private String twitter1;
    private String twitter2;
    private String twitter3;
    private String state;
    private JSONObject rep1;
    private JSONObject rep2;
    private JSONObject rep3;

    public String getTwitter3() {
        return twitter3;
    }

    public String getTwitter1() {
        return twitter1;
    }

    public String getState() {
        return state;
    }

    public String getTwitter2() {
        return twitter2;
    }

    public String getDate1() {
        return date1;
    }

    public String getDate2() {
        return date2;
    }

    public String getDate3() {
        return date3;
    }

    public String getBioguide1() {
        return bioguide1;
    }

    public String getBioguide2() {
        return bioguide2;
    }

    public String getBioguide3() {
        return bioguide3;
    }
    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getWeb1() {
        return web1;
    }

    public String getWeb2() {
        return web2;
    }

    public String getWeb3() {
        return web3;
    }

    public String getPartyText1() {
        return partyText1;
    }

    public String getPartyText2() {
        return partyText2;
    }

    public String getPartyText3() {
        return partyText3;
    }

    public String getFirstName1() {
        return firstName1;
    }

    public String getLastName1() {
        return lastName1;
    }

    public String getFirstName2() {
        return firstName2;
    }

    public String getFirstName3() {
        return firstName3;
    }

    public String getLastName2() {
        return lastName2;
    }

    public String getLastName3() {
        return lastName3;
    }

    @Override
    public void populate(JSONArray data) {
        try {
            rep1 = data.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            rep2 = data.getJSONObject(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            rep3 = data.getJSONObject(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bioguide1 = rep1.optString("bioguide_id");
        bioguide2 = rep2.optString("bioguide_id");
        bioguide3 = rep3.optString("bioguide_id");
        firstName1 = rep1.optString("first_name");
        lastName1 = rep1.optString("last_name");
        firstName2 = rep2.optString("first_name");
        lastName2 = rep2.optString("last_name");
        firstName3 = rep3.optString("first_name");
        lastName3 = rep3.optString("last_name");
        partyText1 = rep1.optString("party");
        partyText2 = rep2.optString("party");
        partyText3 = rep3.optString("party");
        twitter1 = rep1.optString("twitter_id");
        twitter2 = rep2.optString("twitter_id");
        twitter3 = rep3.optString("twitter_id");
        state = rep1.optString("state");

        //SET DATE VARIABLE HERE
        date1 = rep1.optString("term_end");
        date2 = rep2.optString("term_end");
        date3 = rep3.optString("term_end");

       // To be used later
        email1 = rep1.optString("oc_email");
        email2 = rep2.optString("oc_email");
        email3 = rep3.optString("oc_email");
        web1 = rep1.optString("website");
        web2 = rep2.optString("website");
        web3 = rep3.optString("website");

    }
}
