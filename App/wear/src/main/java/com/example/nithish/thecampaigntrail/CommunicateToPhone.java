package com.example.nithish.thecampaigntrail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by Nithish on 3/3/16.
 */
public class CommunicateToPhone extends WearableListenerService implements GoogleApiClient.ConnectionCallbacks {

    private static GoogleApiClient client;
    private String myPath;
    private byte[] communication;



    public CommunicateToPhone(){
        this.myPath = "example";
        this.communication = "example".getBytes();
    }

    public CommunicateToPhone(String path, String message){
        this.myPath = path;
        this.communication = message.getBytes();

    }

    public CommunicateToPhone(String path, byte[] message){
        this.myPath = path;
        this.communication = message;

    }

    public void sendMessage(Context context) {
        client = new GoogleApiClient.Builder(context).addApi(Wearable.API).addConnectionCallbacks( new CommunicateToPhone(myPath, communication )).build();
        client.connect();

    }

    @Override
    public void onConnected(Bundle bundle) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes(client).await();
                for(Node node : nodes.getNodes()) {
                    MessageApi.SendMessageResult sent = Wearable.MessageApi.sendMessage(client, node.getId(), myPath, communication).await();
                }
                client.disconnect();
            }

        }).start();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        String newpath = messageEvent.getPath();
        byte[] newcommunication = messageEvent.getData();
        String toPrint = new String(newcommunication);
        String[] results = toPrint.split( ",\\s*" );

        System.out.println("Message was received.");
        System.out.println(newpath);
        System.out.println(results[1] + results[15] + results[16]);

        if (newpath.equals("/NEXT SCREEN")){

            Intent startRepListActivity = new Intent(getBaseContext(), repListActivity.class);
            startRepListActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startRepListActivity.putExtra("rep1", results[0]);
            startRepListActivity.putExtra("rep2", results[1]);
            startRepListActivity.putExtra("rep3", results[2]);
            startRepListActivity.putExtra("party1", results[3]);
            startRepListActivity.putExtra("party2", results[4]);
            startRepListActivity.putExtra("party3", results[5]);
            startRepListActivity.putExtra("bioguide1", results[6]);
            startRepListActivity.putExtra("bioguide2", results[7]);
            startRepListActivity.putExtra("bioguide3", results[8]);
            startRepListActivity.putExtra("date1", results[9]);
            startRepListActivity.putExtra("date2", results[10]);
            startRepListActivity.putExtra("date3", results[11]);
            startRepListActivity.putExtra("endpoint1", results[12]);
            startRepListActivity.putExtra("endpoint2", results[13]);
            startRepListActivity.putExtra("endpoint3", results[14]);
            startRepListActivity.putExtra("obamap", results[15]);
            startRepListActivity.putExtra("romney", results[16]);
            startRepListActivity.putExtra("county", results[17]);


            startActivity(startRepListActivity);
        }

        if (newpath.equals("/NEXT SCREEN2")){

            Intent startRepListActivity2 = new Intent(getBaseContext(), repListActivity2.class);
            startRepListActivity2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startRepListActivity2);
        }


        if (newpath.equals("/TEST")){
            System.out.println("Message was received REMYBOYZ");
            System.out.println(newcommunication.toString());
        }

    }

}
