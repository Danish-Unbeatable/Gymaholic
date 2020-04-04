package com.example.gymaholic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_chest,btn_back,btn_bisceps,btn_triceps,btn_shoulder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_chest=(Button)findViewById(R.id.chestbtn);
        btn_back=(Button)findViewById(R.id.backbtn);
        btn_bisceps=(Button)findViewById(R.id.biscepbtn);
        btn_triceps=(Button)findViewById(R.id.tribtn);
        btn_shoulder=(Button)findViewById(R.id.shoulderbtn);

        btn_chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,exe.class);
                startActivity(i);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k= new Intent(MainActivity.this,back.class);
                startActivity(k);
            }
        });
        btn_bisceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l= new Intent(MainActivity.this,biscep.class);
                startActivity(l);
            }
        });
        btn_triceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g= new Intent(MainActivity.this,tricep.class);
                startActivity(g);
            }
        });
        btn_shoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v= new Intent(MainActivity.this,shoulder.class);
                startActivity(v);
            }
        });


    }
}
