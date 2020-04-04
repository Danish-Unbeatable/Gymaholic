package com.example.login_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class option extends AppCompatActivity {
    Button buy,sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        buy=(Button)findViewById(R.id.buy1);
        sell=(Button)findViewById(R.id.sold);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s=new Intent(option.this,form.class);
                startActivity(s);
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obuyb();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.home){
            Toast.makeText(this,"Homepage",Toast.LENGTH_LONG).show();
        }
        if(item.getItemId()==R.id.about){
            Toast.makeText(this,"About us",Toast.LENGTH_LONG).show();
            Intent e=new Intent(option.this,about.class);
            startActivity(e);
        }
        if(item.getItemId()==R.id.log){
            Toast.makeText(this,"Log Out",Toast.LENGTH_LONG).show();
            Intent v=new Intent(option.this,MainActivity.class);
            startActivity(v);
        }
        return super.onOptionsItemSelected(item);
    }

    public void obuyb(){
        Intent intent= new Intent(this,buyb.class);
        startActivity(intent);
    }
}



