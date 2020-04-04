package com.example.login_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class buyb extends AppCompatActivity {
    //ListView listView;
    String name[] = {"Computer", "Mechanical", "Civil", "Information Technology", "Electronics and Telecommunication", "Electrical"};
    CircleMenu circleMenu;
    Timer timer;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth=FirebaseAuth.getInstance();


   /*     listView=(ListView)findViewById(R.id.branch);
        ArrayList<String> arrayList= new ArrayList<>();
        arrayList.add("Computer");
        arrayList.add("Mechanical");
        arrayList.add("Civil");
        arrayList.add("Information Technology");
        arrayList.add("Electronics and Telecommunication");
        arrayList.add("Electrical");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.list_items,arrayList);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent k=new Intent(buyb.this,show.class);
                String bname = listView.getItemAtPosition(i).toString().toLowerCase();
                k.putExtra("branch_name", bname);
                startActivity(k);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    }
}*/


        circleMenu = (CircleMenu) findViewById(R.id.circle);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.add, R.drawable.remove)
                .addSubMenu(Color.parseColor("#ff0000"), R.drawable.compp)
                .addSubMenu(Color.parseColor("#ffffff"), R.drawable.mech)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.jcb)
                .addSubMenu(Color.parseColor("#FFFF00"), R.drawable.it)
                .addSubMenu(Color.parseColor("#00ff00"), R.drawable.entc)
                .addSubMenu(Color.parseColor("#800080"), R.drawable.jh)

                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(final int index) {
                        Toast.makeText(getApplicationContext(), "You selected:" + name[index], Toast.LENGTH_SHORT).show();
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent k = new Intent(buyb.this, show.class);
                                String bname = name[index].toLowerCase().trim();
                                k.putExtra("branch_name", bname);
                                startActivity(k);

                            }
                        }, 1500);


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
            Toast.makeText(this,"Homepage",Toast.LENGTH_SHORT).show();
            Intent d=new Intent(buyb.this,option.class);
            startActivity(d);
        }
        if(item.getItemId()==R.id.about){
            Toast.makeText(this,"About us",Toast.LENGTH_SHORT).show();
            Intent e=new Intent(buyb.this,about.class);
            startActivity(e);
        }
        if(item.getItemId()==R.id.log){
            Toast.makeText(this,"Log Out",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
            finish();

            Intent v=new Intent(buyb.this,MainActivity.class);
            startActivity(v);
        }
        return super.onOptionsItemSelected(item);
    }
}