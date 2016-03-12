package com.example.nithish.thecampaigntrail;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "8vB3V1BHoaxbbS476xYB3OFIU";
    private static final String TWITTER_SECRET = "e8eHDfXdQ0ExRg6rP4mKDXZjurhOiovLtfQxutnZG8zjAvszDD";


    private Context context;
    private GoogleApiClient mGoogleApiClient;
    private String mLatitudeText;
    private String mLongitudeText;
    private Location mLastLocation;
    private String inputZip;



    public void sendMessage(String path, byte[] communication){
       new CommunicateToWatch(path, communication).sendMessage(context);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button200); //LocateMe
        final Button button2 = (Button) findViewById(R.id.button5); //Go
        final EditText input = (EditText) findViewById(R.id.editText);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        final String inputZip2 = "94703";
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Wearable.API)  // used for data layer API
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();







        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //This part advances the phone to the Congressional Screen
                System.out.println("Button was clicked");
                Intent startSecondActivity = new Intent(getBaseContext(), SecondActivity.class);
                startSecondActivity.putExtra("zip", inputZip2);
                startActivity(startSecondActivity);


                //sendMessage("/NEXT SCREEN", "hello".getBytes());


                System.out.println("Message was sent after button click");


            }
        });

        // GO button
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                inputZip = input.getText().toString();



                System.out.println("Button was clicked");
                Intent startSecondActivity = new Intent(getBaseContext(), SecondActivity.class);
                //Sending zip code to Second Activity
                startSecondActivity.putExtra("zip", inputZip);
                startActivity(startSecondActivity);

                //sendMessage("/NEXT SCREEN2", "hello".getBytes());

                System.out.println("Message was sent after button click");

            }
        });


    }
//    public void locateMe(View view) {
//        Intent startSecondActivity = new Intent(this, SecondActivity.class);
//        startActivity(startSecondActivity);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

            Toast.makeText(context, "Permissions not granted!", Toast.LENGTH_LONG).show();

            return;
        }
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText = String.valueOf(mLastLocation.getLatitude());
            mLongitudeText = String.valueOf(mLastLocation.getLongitude());
        }

    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}




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
