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



/**
 * Created by Nithish on 3/3/16.
 */
public class CommunicateToWatch extends WearableListenerService implements GoogleApiClient.ConnectionCallbacks {

    private static GoogleApiClient client;
    private String myPath;
    private byte[] communication;

    public byte[] getCommunication() {
        return communication;
    }

    public String getMyPath() {
        return myPath;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public CommunicateToWatch(){
        this.myPath = "wfwrf";
        this.communication = "tfvtfv".getBytes();
    }

    public CommunicateToWatch(String path, String message){
        this.myPath = path;
        this.communication = message.getBytes();

    }

    public CommunicateToWatch(String path, byte[] message){
        this.myPath = path;
        this.communication = message;

    }

    public void sendMessage(Context context) {
        client = new GoogleApiClient.Builder(context).addApi(Wearable.API).addConnectionCallbacks( new CommunicateToWatch(myPath, communication )).build();
        client.connect();

    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        String newpath = messageEvent.getPath();
        byte[] newcommunication = messageEvent.getData();
        String toPrint = new String(newcommunication);
        String[] results = toPrint.split(",\\s*");

        System.out.println("Message was received.");
        System.out.println(newpath);
        System.out.println(newcommunication);

        if (newpath.equals("/DETAILS")){

            Intent startDetailActivity = new Intent(getBaseContext(), ThirdActivity.class);
            startDetailActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startDetailActivity.putExtra("bioguide", results[2]);
            startDetailActivity.putExtra("name", results[0]);
            startDetailActivity.putExtra("party", results[1]);
            startDetailActivity.putExtra("date", results[3]);
            startDetailActivity.putExtra("endpoint", results[4]);
            startActivity(startDetailActivity);
        }

        if (newpath.equals("/TEST")){
            System.out.println("Message was received REMYBOYZ");
            System.out.println(newcommunication.toString());
        }

    }



    @Override
    public void onConnected(Bundle bundle) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes(client).await();
                for(Node node : nodes.getNodes()) {

                    MessageApi.SendMessageResult sent = Wearable.MessageApi.sendMessage(client, node.getId(), myPath, communication).await();
                    System.out.println("We are connected boyz");
                }
                client.disconnect();
            }

        }).start();
    }
}
