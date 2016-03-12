package com.example.nithish.thecampaigntrail.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nithish on 3/9/16.
 */
public class CommitteeResults implements JSONpopulater {

    private JSONObject committee1;
    private JSONObject committee2;
    private JSONObject committee3;
    private String committeeName1;
    private String committeeName2;
    private String committeeName3;

    public JSONObject getCommittee1() {
        return committee1;
    }

    public JSONObject getCommittee2() {
        return committee2;
    }

    public JSONObject getCommittee3() {
        return committee3;
    }

    public String getCommitteeName1() {
        return committeeName1;
    }

    public String getCommitteeName2() {
        return committeeName2;
    }

    public String getCommitteeName3() {
        return committeeName3;
    }

    @Override
    public void populate(JSONArray data) {

        try {
            committee1 = data.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            committee2 = data.getJSONObject(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            committee3 = data.getJSONObject(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        committeeName1 = committee1.optString("name");
        committeeName2 = committee2.optString("name");
        committeeName3 = committee3.optString("name");

    }
}
