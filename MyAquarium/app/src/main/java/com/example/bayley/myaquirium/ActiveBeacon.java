package com.example.bayley.myaquirium;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class ActiveBeacon extends AppCompatActivity {
    private static Button menu_button;
    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_active_beacon); //this is the default view

        /******************fragmentation stuff***************************/
        Configuration config = getResources().getConfiguration();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ActiveFragmentLS ls_fragment = new ActiveFragmentLS();
            fragmentTransaction.replace(android.R.id.content, ls_fragment);
        }else{
            ActiveFragmentPT pm_fragment = new ActiveFragmentPT();
            fragmentTransaction.replace(android.R.id.content, pm_fragment);
        }
        fragmentTransaction.commit();

        /********************ESITMOTE STUFF*********************************/
        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            //this part was part of an example in the tutorial
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                showNotification(
                        "Your gate closes in 47 minutes.",
                        "Current security wait time is 15 minutes, "
                                + "and it's a 5 minute walk from security to the gate. "
                                + "Looks like you've got plenty of time!");
            }

            @Override
            public void onExitedRegion(Region region) {
                showNotification(
                        "leaving the area.",
                        "Current security wait time is 15 minutes, "
                                + "2 mins away from beacon "
                                + "you've left");

            }
        });
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region(
                        "monitored region",
                        //****add my UID for this, can get from cloud or phone app****
                        UUID.fromString("d0d3fa86-ca76-45ec-9bd9-6af473aef152"),
                        12060, 22800));
            }
        });
    }
    /*************************Estimote finished*******************************************/


    public void showNotification(String title, String message){
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[]{notifyIntent},PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    /*public void menuButton(){
        menu_button = (Button)findViewById(R.id.menuButton);
        menu_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent("android.intent.action.com.example.bayley.myaquirium.MainActivity");
                        startActivity(i);
                    }
                }
        );
        }*/
}



