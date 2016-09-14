package com.example.bayley.myaquirium;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.estimote.sdk.SystemRequirementsChecker;

public class MainActivity extends AppCompatActivity {
    private static Button login_button;
    private static Button act_button;
    private static Button start_browser;
    private static Button frag_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adminButton();
        activateButton();
        //for the browser option
        linkButton();
        //fragButton();
    }

    public void adminButton(){
        login_button = (Button)findViewById(R.id.adminButton);
        login_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            Intent i = new Intent("com.example.bayley.myaquirium.SignIn");
                            startActivity(i);
                    }
                }
        );
    }

    public void activateButton(){
        act_button = (Button)findViewById(R.id.activateButton);
        act_button.setOnClickListener(
              new View.OnClickListener(){
                  @Override
                  public void onClick(View v){
                      Intent i = new Intent("com.example.bayley.myaquirium.SearchBeacons");
                      startActivity(i);
                  }
              }
        );
    }

    public void linkButton(){
        start_browser = (Button) findViewById(R.id.uniLinkButton);
        start_browser.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.otago.ac.nz"));
                        startActivity(i);
                    }
                }
        );
    }

    public void fragButton(){
        frag_button = (Button) findViewById(R.id.FragButton);
        frag_button.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(".FragmentTest"));
                        startActivity(i);
                    }
                }
        );
    }
    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }
}