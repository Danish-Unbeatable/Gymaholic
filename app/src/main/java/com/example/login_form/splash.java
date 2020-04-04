package com.example.login_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;



public class splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=2500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

               final Intent s=new Intent(splash.this,MainActivity.class);
                startActivity(s);
                finish();

            }
        },SPLASH_TIME_OUT);

    }
}
