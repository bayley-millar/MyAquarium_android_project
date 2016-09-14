package com.example.bayley.myaquirium;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static Button login_button;
    private static Button menu_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        LoginButton();
        menuButton();

    }

    public void LoginButton(){
        username = (EditText)findViewById(R.id.editText_user);
        password = (EditText)findViewById(R.id.editText_password);
        login_button = (Button)findViewById(R.id.button_logIn);

        login_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(username.getText().toString().equals("user")&&
                                password.getText().toString().equals("password")){
                            Toast.makeText(SignIn.this, "User and password is correct",
                                    Toast.LENGTH_SHORT).show();
                            Intent movePage = new Intent("com.example.bayley.myaquirium.UserPage");
                            startActivity(movePage);
                        }else{
                            Toast.makeText(SignIn.this, "User and password is incorrect",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
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
}
