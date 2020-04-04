package com.example.login_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class display extends AppCompatActivity {
    TextView a, b, c, d, e, f, g, h;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        a = (TextView) findViewById(R.id.bn);
        b = (TextView) findViewById(R.id.ed);
        c = (TextView) findViewById(R.id.pb);
        d = (TextView) findViewById(R.id.pr);
        e = (TextView) findViewById(R.id.na);
        f = (TextView) findViewById(R.id.em);
        g = (TextView) findViewById(R.id.ad);
        h = (TextView) findViewById(R.id.co);
        final String bname = getIntent().getStringExtra("branch_name");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("m");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(member.class).getBname().equals(bname)) {
                        Toast.makeText(display.this, "working", Toast.LENGTH_SHORT).show();
                        String bname = ds.child("bname").getValue().toString();
                        String edi = ds.child("edi").getValue().toString();
                        String pub = ds.child("pub").getValue().toString();
                        String pri = ds.child("pri").getValue().toString();
                        String seller = ds.child("seller").getValue().toString();
                        String ema = ds.child("ema").getValue().toString();
                        String addre = ds.child("addre").getValue().toString();
                        String ph = ds.child("ph").getValue().toString();
                        a.setText("Book Name : "+bname);
                        b.setText("Edition :"+edi);
                        c.setText("Publication :"+pub);
                        d.setText("Price :"+pri);
                        e.setText("Seller Name :"+seller);
                        f.setText("Email id :"+ema);
                        g.setText("Address :"+addre);
                        h.setText("Contact no :"+ph);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

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
            Intent d=new Intent(display.this,option.class);
            startActivity(d);
        }
        if(item.getItemId()==R.id.about){
            Toast.makeText(this,"About us",Toast.LENGTH_LONG).show();
            Intent e=new Intent(display.this,about.class);
            startActivity(e);
        }
        if(item.getItemId()==R.id.log){
            Toast.makeText(this,"Log Out",Toast.LENGTH_LONG).show();
            Intent v=new Intent(display.this,MainActivity.class);
            startActivity(v);
        }
        return super.onOptionsItemSelected(item);
    }
}
