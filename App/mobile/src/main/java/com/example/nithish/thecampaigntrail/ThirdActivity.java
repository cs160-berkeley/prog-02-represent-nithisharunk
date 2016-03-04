package com.example.nithish.thecampaigntrail;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Nithish on 2/27/16.
 */
public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        String[] bills = {"01/09/15:" + "   " + "No More Ghost Money Act", "01/09/15:" + "   " + "Half in Ten Act", "06/13/15:" + "   " +"Calling For Sickle Cell Trait Research"};
        ListAdapter billAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bills);
        ListView billListView = (ListView) findViewById(R.id.billListView);
        billListView.setAdapter(billAdapter);
    }


}
