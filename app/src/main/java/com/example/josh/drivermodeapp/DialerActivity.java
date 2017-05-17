package com.example.josh.drivermodeapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialerActivity extends AppCompatActivity {

    //Store the phone number in here
    String numString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialer_portion);

        //Initialize all the buttons on numpad
        Button oneButton = (Button) findViewById(R.id.oneButton);
        Button twoButton = (Button) findViewById(R.id.twoButton);
        Button threeButton = (Button) findViewById(R.id.threeButton);
        Button fourButton = (Button) findViewById(R.id.fourButton);
        Button fiveButton = (Button) findViewById(R.id.fiveButton);
        Button sixButton = (Button) findViewById(R.id.sixButton);
        Button sevenButton = (Button) findViewById(R.id.sevenButton);
        Button eightButton = (Button) findViewById(R.id.eightButton);
        Button nineButton = (Button) findViewById(R.id.nineButton);
        Button starButton = (Button) findViewById(R.id.starButton);
        Button zeroButton = (Button) findViewById(R.id.zeroButton);
        Button poundButton = (Button) findViewById(R.id.poundButton);
        Button dialerBackButton = (Button) findViewById(R.id.dialerBackButton);
        Button callButton = (Button) findViewById(R.id.callButton);
        Button backspaceButton = (Button) findViewById(R.id.backspaceButton);
        Button contactsButton = (Button) findViewById(R.id.contactsButton);

        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numString = numString + "1";
                ((TextView) findViewById(R.id.numberTextView)).setText(numString);
            }
        });

        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numString = numString + "2";
                ((TextView) findViewById(R.id.numberTextView)).setText(numString);
            }
        });

        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numString = numString + "3";
                ((TextView) findViewById(R.id.numberTextView)).setText(numString);
            }
        });

        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numString = numString + "4";
                ((TextView) findViewById(R.id.numberTextView)).setText(numString);
            }
        });

        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numString = numString + "5";
                ((TextView) findViewById(R.id.numberTextView)).setText(numString);
            }
        });

        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numString = numString + "6";
                ((TextView) findViewById(R.id.numberTextView)).setText(numString);
            }
        });

        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numString = numString + "7";
                ((TextView) findViewById(R.id.numberTextView)).setText(numString);
            }
        });

        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numString = numString + "8";
                ((TextView) findViewById(R.id.numberTextView)).setText(numString);
            }
        });

        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numString = numString + "9";
                ((TextView) findViewById(R.id.numberTextView)).setText(numString);
            }
        });

        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numString = numString + "0";
                ((TextView) findViewById(R.id.numberTextView)).setText(numString);
            }
        });

        backspaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numString.length() != 0) {
                    numString = numString.substring(0, numString.length() - 1);
                    ((TextView) findViewById(R.id.numberTextView)).setText(numString);
                }
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + numString));
                if (ActivityCompat.checkSelfPermission(DialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(DialerActivity.this, new String[]{Manifest.permission.CALL_PHONE},
                            1);
                    return;
                }
                startActivity(callIntent);

                System.out.println(numString);
            }
        });

        dialerBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(DialerActivity.this, MainActivity.class);
                startActivity(backIntent);
            }
        });
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}
