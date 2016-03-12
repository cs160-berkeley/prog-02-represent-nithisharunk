package com.example.nithish.thecampaigntrail;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.wearable.view.DismissOverlayView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;


/**
 * Created by Nithish on 3/1/16.
 */
public class repListActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private DismissOverlayView mDismissOverlayView;
    private GestureDetector mGestureDetector;
    private Context context;
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity
    private String rep1;
    private String rep2;
    private String rep3;
    private String party1;
    private String party2;
    private String party3;
    private String bioguide1;
    private String bioguide2;
    private String bioguide3;
    private String date1;
    private String date2;
    private String date3;
    private String endpoint1;
    private String endpoint2;
    private String endpoint3;
    private String obamap;
    private String romneyp;
    private String county;

    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter

            if (mAccel > 12) {

                Toast toast = Toast.makeText(getApplicationContext(), "New random location!", Toast.LENGTH_LONG);
                toast.show();
                Intent startSecondActivity = new Intent(getBaseContext(), repListActivity2.class);
                startActivity(startSecondActivity);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    public void sendMessage(String path, byte[] communication){
        new CommunicateToPhone(path, communication).sendMessage(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.replist_layout);

        context = getApplicationContext();

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           rep1 = extras.getString("rep1");
            rep2 = extras.getString("rep2");
            rep3 = extras.getString("rep3");
            party1 = extras.getString("party1");
            party2 = extras.getString("party2");
            party3 = extras.getString("party3");
            bioguide1 = extras.getString("bioguide1");
            bioguide2 = extras.getString("bioguide2");
            bioguide3 = extras.getString("bioguide3");
            date1 = extras.getString("date1");
            date2 = extras.getString("date2");
            date3 = extras.getString("date3");
            endpoint1 = extras.getString("endpoint1");
            endpoint2 = extras.getString("endpoint2");
            endpoint3 = extras.getString("endpoint3");
            obamap = extras.getString("obamap");
            romneyp = extras.getString("romney");
            county = extras.getString("county");



        }

        Bundle bundle1 = new Bundle();
        bundle1.putString("rep", rep1);
        bundle1.putString("party", party1);
        bundle1.putString("bioguide", bioguide1);
        bundle1.putString("date",date1);
        bundle1.putString("endpoint", endpoint1);
        Fragment1 frag1 = new Fragment1();
        frag1.setArguments(bundle1);

        Bundle bundle2 = new Bundle();
        bundle2.putString("rep", rep2);
        bundle2.putString("party", party2);
        bundle2.putString("bioguide", bioguide2);
        bundle2.putString("date",date2);
        bundle2.putString("endpoint", endpoint2);
        Fragment2 frag2 = new Fragment2();
        frag2.setArguments(bundle2);

        Bundle bundle3 = new Bundle();
        bundle3.putString("rep", rep3);
        bundle3.putString("party", party3);
        bundle3.putString("bioguide", bioguide3);
        bundle3.putString("date",date3);
        bundle3.putString("endpoint", endpoint3);
        Fragment3 frag3 = new Fragment3();
        frag3.setArguments(bundle3);

        Bundle bundle4 = new Bundle();
        bundle4.putString("op",obamap);
        bundle4.putString("rp",romneyp);
        bundle4.putString("county", county);
        Fragment4 frag4 = new Fragment4();
        frag4.setArguments(bundle4);


        final FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(frag1);
        adapter.addFragment(frag2);
        adapter.addFragment(frag3);
        adapter.addFragment(frag4);

        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mGestureDetector = new GestureDetector(this, new LongPressListener());


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event)
                || super.dispatchTouchEvent(event);
    }

    private class LongPressListener extends
            GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(MotionEvent event) {
            mDismissOverlayView.show();

        }

    }



    }

