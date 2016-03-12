package com.example.nithish.thecampaigntrail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Fragment4 extends Fragment {

    private Context context;
    private TextView county;
    private TextView obamap;
    private TextView romneyp;
    private String o;
    private String r;
    private String c;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.vote_layout, container, false);

        context = getActivity().getApplicationContext();

        county = (TextView) myView.findViewById(R.id.textView8);
        obamap = (TextView) myView.findViewById(R.id.textView11);
        romneyp = (TextView) myView.findViewById(R.id.textView13);

        o = getArguments().getString("op");
        r = getArguments().getString("rp");
        c = getArguments().getString("county");

        county.setText(c);
        obamap.setText(o);
        romneyp.setText(r);

        return myView;
    }

}