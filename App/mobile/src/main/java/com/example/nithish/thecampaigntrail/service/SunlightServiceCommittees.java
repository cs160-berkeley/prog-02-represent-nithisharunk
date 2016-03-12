package com.example.nithish.thecampaigntrail.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.nithish.thecampaigntrail.data.CommitteeResults;
import com.example.nithish.thecampaigntrail.data.Results;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Nithish on 3/8/16.
 */
public class SunlightServiceCommittees {

    private SunlightServiceCallback callback;
    private String bioguide;
    private Exception error;

    public SunlightServiceCommittees(SunlightServiceCallback callback) {
        this.callback = callback;
    }

    public void refreshReps (final String bioguide){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

                String YQL = String.format("%s", bioguide);

                String endpoint = String.format("http://congress.api.sunlightfoundation.com/committees?member_ids=%s&apikey=7d7a7dd29e104eadaf3481fdf3535344", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    return result.toString();
                } catch (Exception e) {
                    error = e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if (s == null && error != null){
                    callback.serviceFailure(error);
                    return;
                }

                try {
                    JSONObject data = new JSONObject(s);
                    JSONArray apiResults = data.optJSONArray("results");


                    CommitteeResults newResult = new CommitteeResults();
                    newResult.populate(apiResults);

                    callback.serviceSuccessComm(newResult);



                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }

            }
        }.execute(bioguide);
    }

    public class zipException extends Exception {
        public zipException(String detailMessage) {
            super(detailMessage);
        }
    }
}
