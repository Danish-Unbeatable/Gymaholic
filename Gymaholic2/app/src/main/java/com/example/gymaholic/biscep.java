package com.example.gymaholic;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class biscep extends AppCompatActivity {
    VideoView videoView;
    Chronometer chronometer;
    ImageButton imgstrt,imgstop;
    private boolean isresume;
    Handler handler;
    long tMilliSec,tStart,tBuff,tUpdate= 0L;
    int sec,min,millisec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biscep);
        videoView=(VideoView)findViewById(R.id.vb);
        String video_path="android.resource://"+getPackageName() + "/" + R.raw.biscep;
        Uri uri=Uri.parse(video_path);
        videoView.setVideoURI(uri);

        MediaController mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        chronometer=(Chronometer)findViewById(R.id.chrono);
        imgstrt=(ImageButton)findViewById(R.id.start);
        imgstop=(ImageButton)findViewById(R.id.pause);
        handler=new Handler();
        imgstrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isresume)
                {
                    tStart= SystemClock.uptimeMillis();
                    handler.postDelayed(runnable,0);
                    chronometer.start();
                    isresume=true;
                    imgstop.setVisibility(View.GONE);
                    imgstrt.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause));
                }
                else
                {
                    tBuff+=tMilliSec;
                    handler.removeCallbacks(runnable);
                    chronometer.stop();
                    isresume=false;
                    imgstop.setVisibility(View.VISIBLE);
                    imgstrt.setImageDrawable(getResources().getDrawable(R.drawable.ic_play));

                }
            }
        });
        imgstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isresume){
                    imgstrt.setImageDrawable(getResources().getDrawable(R.drawable.ic_play));
                    tMilliSec=0L;
                    tStart=0L;
                    tBuff=0L;
                    tUpdate=0L;
                    sec=0;
                    min=0;
                    millisec=0;
                    chronometer.setText("00:00:00");
                }
            }
        });



    }
    public Runnable runnable=new Runnable() {
        @Override
        public void run() {
            tMilliSec= SystemClock.uptimeMillis() - tStart;
            tUpdate=tBuff+tMilliSec;
            sec=(int) (tUpdate/1000);
            min=sec/60;
            sec=sec%60;
            millisec=(int) (tUpdate%100);
            chronometer.setText(String.format("%02d",min)+":"+String.format("%02d",sec)+":"+String.format("%02d",millisec));
            handler.postDelayed(this,60);


        }
    };
}
