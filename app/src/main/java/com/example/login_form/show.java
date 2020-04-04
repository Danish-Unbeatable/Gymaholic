package com.example.login_form;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class show extends AppCompatActivity {
    ListView lstview;
    String bname;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();


        bname = getIntent().getStringExtra("branch_name");
        Toast.makeText(this, "" + bname, Toast.LENGTH_SHORT).show();

        lstview = (ListView) findViewById(R.id.sub);
        // Write a message to the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("m");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> arrayList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (bname.equals(ds.getValue(member.class).getBran().toLowerCase()))
                        arrayList.add(ds.getValue(member.class).getBname());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.list_items, arrayList);
                lstview.setAdapter(arrayAdapter);
                lstview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent f = new Intent(show.this, display.class);
                        String bname = lstview.getItemAtPosition(i).toString().toLowerCase();
                        f.putExtra("branch_name", bname);
                        startActivity(f);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        lstview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                final int which_item = i;
                new AlertDialog.Builder(show.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are You sure?")
                        .setMessage("do you want to delete this book")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference().child("m").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                            if (ds.getValue(member.class).getBname().toLowerCase().equals(adapterView.getItemAtPosition(which_item).toString().toLowerCase())) {
                                                FirebaseDatabase.getInstance().getReference().child("m").child(ds.getKey()).removeValue();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Toast.makeText(this, "Homepage", Toast.LENGTH_SHORT).show();
            Intent d = new Intent(show.this, option.class);
            startActivity(d);
        }
        if (item.getItemId() == R.id.about) {
            Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
            Intent e = new Intent(show.this, about.class);
            startActivity(e);
        }
        if (item.getItemId() == R.id.log) {
            Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
            finish();

            Intent v = new Intent(show.this, MainActivity.class);
            startActivity(v);
        }
        return super.onOptionsItemSelected(item);
    }
}
