//DriverModeApp
//TimeAndDateFragment.java
//Displays the time and date on all activities

package com.example.josh.drivermodeapp;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TimeAndDateFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.date_and_time_fragment2, container, false);

        return view;
    }
}