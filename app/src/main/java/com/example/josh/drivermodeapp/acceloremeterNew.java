/*
This class needs to implement the LocationListener
LocationListener will come with its generated functions
we will utalize the onLocationChanged() method.
 */

package com.example.josh.drivermodeapp;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class acceloremeterNew extends Activity implements LocationListener {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    BroadcastReceiver receiver = null;
    public String storeSenderNumber;

    //use for text messages
    String phoneNumber = ""; //given phone number
    String replyText = "Okay";
    //String drivingText = "I am driving.";
    //String yesText = "Yes";
    //String homeText = "I'll be home soon.";
    double currSpeed = 0; //for the current speed


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceloremeter_new);

        Button menuButton = (Button)findViewById(R.id.menuButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acceloremeterNew.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //set only landscame mode
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        //we need a reference to the locationManager
        LocationManager location = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //update the variable
        //change the 0, 0 depending how often you want to recieve updates
        //the < the number the faster battery drain

        // Assume thisActivity is the current activity
        //int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    2);
            return;
        }
        location.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        this.onLocationChanged(null);

        //*****************************************************************************
        //functions for textMessage
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context arr0, Intent arr1) {
                processReceive(arr0, arr1);
            }
        };
        registerReceiver(receiver, filter);
    }


    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void processReceive(Context context, Intent intent){
        Toast.makeText(context, "CO tin nhan", Toast.LENGTH_LONG).show();
        TextView contactText = (TextView)findViewById(R.id.contactText);
        TextView bodyText = (TextView)findViewById(R.id.contentText);

        Bundle bundle = intent.getExtras();
        Object[] objArr = (Object[])bundle.get("pdus");
        String body = "";
        String contact = "";

        for(int i = 0; i < objArr.length; i++){
            SmsMessage smsMsg = SmsMessage.createFromPdu((byte[])objArr[i]);
            String smsBody = smsMsg.getMessageBody();
            String senderNumber = smsMsg.getDisplayOriginatingAddress();
            storeSenderNumber = senderNumber;
            contact = "From: " + senderNumber;
            body = smsBody;
        }
        contactText.setText(contact);
        bodyText.setText(body);
    }


    @Override
    public void onLocationChanged(Location location) {
        //get reference to the textView
        TextView speedNumber = (TextView)findViewById(R.id.speedText);

        //must check what the speed is
        if(location == null){ //menas no speed
            speedNumber.setText("0");
        }
        else{ //there is spseed
            float currSpeed = location.getSpeed();

            //change to mph
            float mph = (float) (currSpeed * 2.23693629);

            speedNumber.setText((int) mph);
        }

    }



//**************************************************************************************************
//**************************************************************************************************
    //create a function to update the current speed;
    //this is to know how to answer each function
    public void getCurrSpeed(){
        TextView tempText = (TextView)this.findViewById(R.id.speedText);
        String stringSpeed = tempText.getText().toString();
        //change to a double
        Double curr = Double.parseDouble(stringSpeed);
        //update the curr speed
        currSpeed = curr;

    }


//**************************************************************************************************
//**************************************************************************************************
    //create a function that will check which phone number is current
    public String getNumber(){
       String currNumber = storeSenderNumber;
        if(currNumber.equals(" ")){
            return "No contact";
        }
        else{
            currNumber = " ";
            return currNumber;
        }
    }



//************************************************************************************************
//**************************************************************************************************
//**************************************************************************************************
    //functions use to reply automated messages
    /*@
        for the automated ok reply
        the automated reply to the given text number
     */
    public void runOkReply(View v){
        //run function to check if a phone number is valid
        phoneNumber = this.getNumber();

        //se the correct reply text string
        replyText = "Ok";
        //ask permission to run
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

    }

    //set the driving reply
    public void runDrivingReply(View v){
        //run function to check if a phone number is valid
        phoneNumber = this.getNumber();

        //se the correct reply text string
        replyText = "I'm driving.";
        //ask permission to run
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

    }

    //set the ill be home reply
    public void runHomeReply(View v){

        //run function to check if a phone number is valid
        phoneNumber = this.getNumber();

        //se the correct reply text string
        replyText = "I'll be home soon.";
        //ask permission to run
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

    }


    //set the ill be home reply
    public void runYesReply(View v){

        //run function to check if a phone number is valid
        phoneNumber = this.getNumber();

        //se the correct reply text string
        replyText = "Yes.";
        //ask permission to run
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

    }




    //the permissionFunction
    //second function for permission

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, replyText, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }









//**************************************************************************************************
//**************************************************************************************************
//**************************************************************************************************

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

