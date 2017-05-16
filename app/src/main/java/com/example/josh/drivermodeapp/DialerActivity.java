package com.example.josh.drivermodeapp;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class DialerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialer_portion);

        Button oneButton = (Button)findViewById(R.id.oneButton);
        Button twoButton = (Button)findViewById(R.id.twoButton);
        Button threeButton = (Button)findViewById(R.id.threeButton);
        Button foutButton = (Button)findViewById(R.id.fourButton);
        Button fiveButton = (Button)findViewById(R.id.fiveButton);
        Button sixButton = (Button)findViewById(R.id.sixButton);
        Button sevenButton = (Button)findViewById(R.id.sevenButton);
        Button eightButton = (Button)findViewById(R.id.eightButton);
        Button nineButton = (Button)findViewById(R.id.nineButton);
        Button starButton = (Button)findViewById(R.id.starButton);
        Button zeroButton = (Button)findViewById(R.id.zeroButton);
        Button poundButton = (Button)findViewById(R.id.poundButton);
        Button backButton = (Button)findViewById(R.id.backButton);
        Button callButton = (Button)findViewById(R.id.callButton);
        Button backspaceButton = (Button)findViewById(R.id.backspaceButton);
        Button contactsButton = (Button)findViewById(R.id.contactsButton);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}
