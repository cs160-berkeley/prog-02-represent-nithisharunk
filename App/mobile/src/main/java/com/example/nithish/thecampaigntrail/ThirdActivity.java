package com.example.nithish.thecampaigntrail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nithish.thecampaigntrail.data.BillResults;
import com.example.nithish.thecampaigntrail.data.CommitteeResults;
import com.example.nithish.thecampaigntrail.data.GeoResults;
import com.example.nithish.thecampaigntrail.data.Results;
import com.example.nithish.thecampaigntrail.service.SunlightService;
import com.example.nithish.thecampaigntrail.service.SunlightServiceBills;
import com.example.nithish.thecampaigntrail.service.SunlightServiceCallback;
import com.example.nithish.thecampaigntrail.service.SunlightServiceCommittees;
import com.squareup.picasso.Picasso;

/**
 * Created by Nithish on 2/27/16.
 */
public class ThirdActivity extends Activity implements SunlightServiceCallback {


    private SunlightServiceCommittees commService;
    private SunlightService nameService;
    private SunlightServiceBills billService;
    private ProgressDialog dialog;
    private ImageView pic;
    private TextView repName;
    private TextView party;
    private TextView date;
    private TextView committee1;
    private TextView committee2;
    private TextView committee3;
    private TextView bill1;
    private TextView bill2;
    private TextView bill3;
    private String bioguide;
    private String name;
    private String partyString;
    private String dateString;
    private String endpoint;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        repName = (TextView) findViewById(R.id.textView4);
        party = (TextView) findViewById(R.id.textView6);
        date = (TextView) findViewById(R.id.textView8);
        committee1 = (TextView) findViewById(R.id.textView10);
        committee2 = (TextView) findViewById(R.id.textView11);
        committee3 = (TextView) findViewById(R.id.textView31);
        bill1 = (TextView) findViewById(R.id.textView32);
        bill2 = (TextView) findViewById(R.id.textView33);
        bill3 = (TextView) findViewById(R.id.textView34);
        pic = (ImageView) findViewById(R.id.imageView3);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bioguide = extras.getString("bioguide");
            name = extras.getString("name");
            partyString = extras.getString("party");
            dateString = extras.getString("date");
            endpoint = extras.getString("endpoint");
        }

        repName.setText("-" + name + "-");

        if (partyString.equals("Democrat")){
            party.setText(partyString);
            party.setTextColor(Color.BLUE);
        }

        if (partyString.equals("Republican")){
            party.setText(partyString);
            party.setTextColor(Color.RED);
        }
        if (partyString.equals("Independent")){
            party.setText(partyString);
            party.setTextColor(Color.GREEN);
        }


        Picasso.with(this).load(endpoint).into(pic);

        date.setText(dateString);

        commService = new SunlightServiceCommittees(this);
        billService = new SunlightServiceBills(this);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...please wait");
        dialog.show();

        commService.refreshReps(bioguide);
        billService.refreshReps(bioguide);


    }


    @Override
    public void serviceSuccess(Results result) {


    }

    @Override
    public void serviceSuccessComm(CommitteeResults commresult) {
        dialog.hide();
        committee1.setText("- " + commresult.getCommitteeName1());
        committee2.setText("- " + commresult.getCommitteeName2());
        committee3.setText("- " + commresult.getCommitteeName3());

    }

    @Override
    public void serviceSuccessBill(BillResults billResult) {
        dialog.hide();
        bill1.setText("- " + billResult.getBillName1());
        bill2.setText("- " + billResult.getBillName2());
        bill3.setText("- " + billResult.getBillName3());

    }

    @Override
    public void serviceSuccessGeo(GeoResults geoResult) {

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
