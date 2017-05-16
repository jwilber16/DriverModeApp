package com.example.josh.drivermodeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainMenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.main_menu_fragment, container, false);

        //Call button open dialer activity
        Button callButton = (Button)view.findViewById(R.id.phoneButton);
        callButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DialerActivity.class);
                startActivity(intent);
            }
        });

        //Media button open media activity
        Button mediaButton = (Button)view.findViewById(R.id.mediaButton);
        mediaButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DialerActivity.class);
                startActivity(intent);
            }
        });

        //Navigation button opens navigation activity
        Button navButton = (Button)view.findViewById(R.id.navButton);
        navButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open navigation activity
            }
        });
        return view;
    }




}
