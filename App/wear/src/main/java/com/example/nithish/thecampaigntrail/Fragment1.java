package com.example.nithish.thecampaigntrail;



import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Context;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Fragment1  extends Fragment {

    public Context context;
    public TextView name;
    public TextView party;
    public String name1;
    public String party1;
    public String bioguide;
    public String date;
    public String endpoint;




	   @Override
       public View onCreateView(LayoutInflater inflater, ViewGroup container,
               Bundle savedInstanceState) {

           View myView = inflater.inflate(R.layout.pic1, container, false);
           Button myButton = (Button) myView.findViewById(R.id.button);
           context = getActivity().getApplicationContext();

           name = (TextView) myView.findViewById(R.id.textView2);
           party = (TextView) myView.findViewById(R.id.textView3);

            name1 = getArguments().getString("rep");
            party1 = getArguments().getString("party");
            bioguide = getArguments().getString("bioguide");
            date = getArguments().getString("date");
            endpoint = getArguments().getString("endpoint");

           name.setText(name1);
           if (party1.equals("Democrat")){
               party.setText(party1);
               party.setTextColor(Color.BLUE);
           }
           if (party1.equals("Republican")){
               party.setText(party1);
               party.setTextColor(Color.RED);
           }
           if (party1.equals("Independent")){
               party.setText(party1);
               party.setTextColor(Color.GREEN);
           }




           myButton.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {

                   System.out.println(" Watch button was clicked");
                   CommunicateToPhone details = new CommunicateToPhone("/DETAILS",(name1 + "," + party1 + "," + bioguide + "," + date + "," + endpoint).getBytes());

                   details.sendMessage(context);


                   System.out.println("Message was sent after button click");


               }
           });
           return myView;
       }


	   
	   public static void stopCycling() {
			// TODO Auto-generated method stub
			
		}
   }
