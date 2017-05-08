//DriverModeApp
//TimeAndDateFragment.java
//Displays the time and date on all activities

package com.example.josh.drivermodeapp;

import java.text.DateFormat;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

import static java.text.DateFormat.MEDIUM;
import static java.text.DateFormat.SHORT;


public class TimeAndDateFragment extends Fragment {

    TextView dateAndTimeText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.date_and_time_fragment, container, false);
        dateAndTimeText = (TextView) view.findViewById(R.id.dateAndTime);

        String currentTime = DateFormat.getDateTimeInstance(MEDIUM, SHORT).format(new Date());

        String dateAndTime =  currentTime;

        dateAndTimeText.setText(dateAndTime);


        return view;
    }
}
