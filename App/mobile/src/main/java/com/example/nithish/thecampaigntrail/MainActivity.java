package com.example.nithish.thecampaigntrail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;

public class MainActivity extends AppCompatActivity {

    private Context context;

    public void sendMessage(String path, byte[] communication){
       new CommunicateToWatch(path, communication).sendMessage(context);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button200);
        final Button button2 = (Button) findViewById(R.id.button5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();





        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //This part advances the phone to the Congressional Screen
                System.out.println("Button was clicked");
                Intent startSecondActivity = new Intent(getBaseContext(), SecondActivity.class);
                startActivity(startSecondActivity);

                sendMessage("/NEXT SCREEN","hello".getBytes());


                System.out.println("Message was sent after button click");


            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                System.out.println("Button was clicked");
                Intent startSecondActivity = new Intent(getBaseContext(), SecondActivity.class);
                startActivity(startSecondActivity);

                sendMessage("/NEXT SCREEN2", "hello".getBytes());

                System.out.println("Message was sent after button click");

            }
        });


    }
//    public void locateMe(View view) {
//        Intent startSecondActivity = new Intent(this, SecondActivity.class);
//        startActivity(startSecondActivity);
//    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
