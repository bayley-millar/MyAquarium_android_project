package com.example.bayley.myaquirium;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchBeacons extends AppCompatActivity {
    private static Button menu_button;
    private static Button example_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_beacons);
        menuButton();
        exampleButton();
    }

    public void menuButton(){
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
    }

    public void exampleButton(){
        example_button = (Button)findViewById(R.id.exampleButton);
        example_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent("com.example.bayley.myaquirium.ActiveBeacon");
                        startActivity(i);
                    }
                }
        );
    }
}
