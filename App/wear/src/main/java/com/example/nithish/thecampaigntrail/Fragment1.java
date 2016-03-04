package com.example.nithish.thecampaigntrail;



import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Context;


public class Fragment1  extends Fragment {

    public Context context;





	   @Override
       public View onCreateView(LayoutInflater inflater, ViewGroup container,
               Bundle savedInstanceState) {

           View myView = inflater.inflate(R.layout.pic1, container, false);
           Button myButton = (Button) myView.findViewById(R.id.button);
           context = getActivity().getApplicationContext();



           myButton.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {

                   System.out.println(" Watch button was clicked");
                   CommunicateToPhone details = new CommunicateToPhone("/DETAILS","hello".getBytes());

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
