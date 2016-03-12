package com.example.nithish.thecampaigntrail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nithish.thecampaigntrail.data.BillResults;
import com.example.nithish.thecampaigntrail.data.CommitteeResults;
import com.example.nithish.thecampaigntrail.data.GeoResults;
import com.example.nithish.thecampaigntrail.data.Results;
import com.example.nithish.thecampaigntrail.service.GeocodingService;
import com.example.nithish.thecampaigntrail.service.SunlightService;
import com.example.nithish.thecampaigntrail.service.SunlightServiceCallback;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Nithish on 2/27/16.
 */
public class SecondActivity extends Activity implements SunlightServiceCallback {

    private SunlightService service;
    private GeocodingService geo;
    private ProgressDialog dialog;
    private TextView rep1Name;
    private TextView rep2Name;
    private TextView rep3Name;
    private TextView partyText1;
    private TextView partyText2;
    private TextView partyText3;
    private ImageView pic1;
    private ImageView pic2;
    private ImageView pic3;
    private String zip;
    private String bioguide1;
    private String bioguide2;
    private String bioguide3;

    private String web1;
    private String web2;
    private String web3;
    private String email1;
    private String email2;
    private String email3;

    private String twitID1;
    private String twitID2;
    private String twitID3;
    private ArrayList<Tweet> tweets;
    private String tweet1;
    private TextView tweetToDisplay1;
    private TextView tweetToDisplay2;
    private TextView tweetToDisplay3;
    private JSONArray voteData;
    private JSONObject county;
    private String countyName;
    private String countyFound;
    private String state;
    private String obamaPercent;
    private String romneyPercent;
    private String toCheck;

    private JSONObject JSONDerulo;

    private String date1;
    private String date2;
    private String date3;
    private String rep1;
    private String rep2;
    private String rep3;
    private String pic1endpoint;
    private String pic2endpoint;
    private String pic3endpoint;

    private Context context;

    public void sendMessage(String path, byte[] communication){
        new CommunicateToWatch(path, communication).sendMessage(context);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("better.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        context = getApplicationContext();
         rep1Name = (TextView) findViewById(R.id.textView5);
         rep2Name = (TextView) findViewById(R.id.textView6);
         rep3Name = (TextView) findViewById(R.id.textView25);
         partyText1 = (TextView) findViewById(R.id.partyText1);
         partyText2 = (TextView) findViewById(R.id.partyText2);
         partyText3 = (TextView) findViewById(R.id.partyText3);
         pic1 = (ImageView) findViewById(R.id.image1);
         pic2 = (ImageView) findViewById(R.id.imageView2);
         pic3 = (ImageView) findViewById(R.id.image3);
         tweetToDisplay1 = (TextView) findViewById(R.id.textView16);
         tweetToDisplay2 = (TextView) findViewById(R.id.textView24);
         tweetToDisplay3 = (TextView) findViewById(R.id.textView30);
         String t1 = "Great news! #Oakland received over $7m from CA Dept. of Housing & Community Development to build affordable housing for vets and families!";
         String t2 = "Kalamazoo and Hesston shootings provide yet another call for Congress to act to #StopGunViolence.";
         String t3 = "The families in #Flint, in Jackson, and in every community threatened by #lead in their #drinkingwater deserve action now #CleanWater";
         String t4 = "As we transition to a clean energy economy, Congress must continue to invest in innovation happening in places like SD to meet this goal.";
         String t5 = "Excited to hear from innovators at SXSW this weekend to see how we can bring their shake-things-up mentality to DC.";
         String t6 = "Last night's #GOPDebate made it clear who was prepared and who has the best grasp of the issues facing our country.";



        String Derulo = loadJSONFromAsset();
        try {
            JSONDerulo = new JSONObject(Derulo);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            zip = extras.getString("zip");
        }
        if (zip != null){
            tweetToDisplay1.setText(t6);
            tweetToDisplay2.setText(t3);
            tweetToDisplay3.setText(t5);
        }
        if (zip.equals("94703")){
            tweetToDisplay1.setText(t1);
            tweetToDisplay2.setText(t2);
            tweetToDisplay3.setText(t3);
        }
        if (zip.equals("92127")){
            tweetToDisplay1.setText(t4);
            tweetToDisplay2.setText(t5);
            tweetToDisplay3.setText(t3);
        }


        service = new SunlightService(this);
        geo = new GeocodingService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....please wait.");
        dialog.show();

        geo.refreshReps(zip);
        service.refreshReps(zip);


    }
    public void displayEmail1(View view){
        Toast.makeText(this,email1,Toast.LENGTH_LONG).show();
    }
    public void displayEmail2(View view){
        Toast.makeText(this,email2,Toast.LENGTH_LONG).show();
    }
    public void displayEmail3(View view){
        Toast.makeText(this,email3,Toast.LENGTH_LONG).show();
    }

    public void openWeb(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(web1));
        startActivity(browserIntent);
    }
    public void openWeb2(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(web2));
        startActivity(browserIntent);
    }
    public void openWeb3(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(web3));
        startActivity(browserIntent);
    }

    public void moreDetails(View view) {
        Intent startThirdActivity = new Intent(this, ThirdActivity.class);
        startThirdActivity.putExtra("bioguide", bioguide1);
        startThirdActivity.putExtra("name", rep1);
        startThirdActivity.putExtra("party", partyText1.getText());
        startThirdActivity.putExtra("date", date1);
        startThirdActivity.putExtra("endpoint", pic1endpoint);
        startActivity(startThirdActivity);
    }

    public void moreDetails2(View view) {
        Intent startThirdActivity = new Intent(this, ThirdActivity.class);
        startThirdActivity.putExtra("bioguide", bioguide2);
        startThirdActivity.putExtra("name", rep2);
        startThirdActivity.putExtra("party", partyText2.getText());
        startThirdActivity.putExtra("date", date2);
        startThirdActivity.putExtra("endpoint", pic2endpoint);
        startActivity(startThirdActivity);
    }

    public void moreDetails3(View view) {
        Intent startThirdActivity = new Intent(this, ThirdActivity.class);
        startThirdActivity.putExtra("bioguide", bioguide3);
        startThirdActivity.putExtra("name", rep3);
        startThirdActivity.putExtra("party", partyText3.getText());
        startThirdActivity.putExtra("date", date3);
        startThirdActivity.putExtra("endpoint", pic3endpoint);
        startActivity(startThirdActivity);
    }

    @Override
    public void serviceSuccessGeo(GeoResults geoResult) {

        countyName = geoResult.getCounty();
        state = geoResult.getState();
        toCheck = countyName + ", " + state;

        try {
            county = JSONDerulo.getJSONObject(toCheck);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        obamaPercent = county.optString("obama");
        romneyPercent = county.optString("romney");

    }

    @Override
    public void serviceSuccess(Results result) {
        dialog.hide();
        context = getApplicationContext();

        web1 = result.getWeb1();
        web2 = result.getWeb2();
        web3 = result.getWeb3();
        email1 = result.getEmail1();
        email2 = result.getEmail2();
        email3 = result.getEmail3();

        // NVM THIS IS TOTALLY RIGHT FAM
        rep1Name.setText(result.getFirstName1());
        rep1Name.append(" " + result.getLastName1());
        rep2Name.setText(result.getFirstName2());
        rep2Name.append(" " + result.getLastName2());
        rep3Name.setText(result.getFirstName3());
        rep3Name.append(" " + result.getLastName3());


        //Getting twitter IDs
        twitID1 = result.getTwitter1();
        twitID2 = result.getTwitter2();
        twitID3 = result.getTwitter3();

        //Getting bioguide
        bioguide1 = result.getBioguide1();
        bioguide2 = result.getBioguide2();
        bioguide3 = result.getBioguide3();

        //Setting the pictures for each representative

        pic1endpoint = "https://theunitedstates.io/images/congress/225x275/" + bioguide1 + ".jpg";
        Picasso.with(this).load(pic1endpoint).into(pic1);

        pic2endpoint = "https://theunitedstates.io/images/congress/225x275/" + bioguide2 + ".jpg";
        Picasso.with(this).load(pic2endpoint).into(pic2);

        pic3endpoint = "https://theunitedstates.io/images/congress/225x275/" + bioguide3 + ".jpg";
        Picasso.with(this).load(pic3endpoint).into(pic3);

        //Strings to send as extras

        date1 = result.getDate1();
        date2 = result.getDate2();
        date3 = result.getDate3();

        rep1 = result.getFirstName1() + " " + result.getLastName1();
        rep2 = result.getFirstName2() + " " + result.getLastName2();
        rep3 = result.getFirstName3() + " " + result.getLastName3();

        //Change party from letter to word

        if (result.getPartyText1().equals("D")){
            partyText1.setText("Democrat");
            partyText1.setTextColor(Color.BLUE);
        }
        if (result.getPartyText1().equals("R")){
            partyText1.setText("Republican");
            partyText1.setTextColor(Color.RED);
        }
        if (result.getPartyText1().equals("I")){
            partyText1.setText("Independent");
            partyText1.setTextColor(Color.GREEN);
        }
        if (result.getPartyText2().equals("D")){
            partyText2.setText("Democrat");
            partyText2.setTextColor(Color.BLUE);
        }
        if (result.getPartyText2().equals("R")){
            partyText2.setText("Republican");
            partyText2.setTextColor(Color.RED);
        }
        if (result.getPartyText2().equals("I")){
            partyText2.setText("Independent");
            partyText2.setTextColor(Color.GREEN);
        }
        if (result.getPartyText3().equals("D")){
            partyText3.setText("Democrat");
            partyText3.setTextColor(Color.BLUE);
        }
        if (result.getPartyText3().equals("R")){
            partyText3.setText("Republican");
            partyText3.setTextColor(Color.RED);
        }
        if (result.getPartyText3().equals("I")){
            partyText3.setText("Independent");
            partyText3.setTextColor(Color.GREEN);
        }

        sendMessage("/NEXT SCREEN", (rep1 + "," + rep2 + "," + rep3 + "," + partyText1.getText() + "," + partyText2.getText() + "," + partyText3.getText() + "," + bioguide1 + "," + bioguide2 + "," + bioguide3 + "," + date1 + "," + date2 + "," + date3 + "," + pic1endpoint + "," + pic2endpoint + "," + pic3endpoint + "," + obamaPercent + "," + romneyPercent + "," + toCheck).getBytes());



    }

    @Override
    public void serviceSuccessComm(CommitteeResults commresult) {

    }

    @Override
    public void serviceSuccessBill(BillResults billResult) {

    }



    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
    }
}
