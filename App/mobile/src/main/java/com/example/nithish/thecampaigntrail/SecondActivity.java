package com.example.nithish.thecampaigntrail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Nithish on 2/27/16.
 */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
    }

    public void moreDetails(View view) {
        Intent startThirdActivity = new Intent(this, ThirdActivity.class);
        startActivity(startThirdActivity);
    }
}
