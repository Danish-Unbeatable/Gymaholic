package com.example.login_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
            Intent d=new Intent(about.this,option.class);
            startActivity(d);
        }
        if(item.getItemId()==R.id.about){
            Toast.makeText(this,"About us",Toast.LENGTH_LONG).show();

        }
        if(item.getItemId()==R.id.log){
            Toast.makeText(this,"Log Out",Toast.LENGTH_LONG).show();
            Intent v=new Intent(about.this,MainActivity.class);
            startActivity(v);
        }
        return super.onOptionsItemSelected(item);
    }
}
