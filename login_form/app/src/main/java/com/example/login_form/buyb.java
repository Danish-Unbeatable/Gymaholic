package com.example.login_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class buyb extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyb);
        listView=(ListView)findViewById(R.id.branch);
        ArrayList<String> arrayList= new ArrayList<>();
        arrayList.add("Computer");
        arrayList.add("Mechanical");
        arrayList.add("Civil");
        arrayList.add("Information Technology");
        arrayList.add("Electronics and Telecommunication");
        arrayList.add("Electrical");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.home){
            Toast.makeText(this,"Homepage",Toast.LENGTH_LONG).show();
            Intent d=new Intent(buyb.this,option.class);
            startActivity(d);
        }
        if(item.getItemId()==R.id.about){
            Toast.makeText(this,"About us",Toast.LENGTH_LONG).show();
            Intent e=new Intent(buyb.this,about.class);
            startActivity(e);
        }
        if(item.getItemId()==R.id.log){
            Toast.makeText(this,"Log Out",Toast.LENGTH_LONG).show();
            Intent v=new Intent(buyb.this,MainActivity.class);
            startActivity(v);
        }
        return super.onOptionsItemSelected(item);
    }
}
