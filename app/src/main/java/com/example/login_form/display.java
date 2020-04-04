package com.example.login_form;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class display extends AppCompatActivity {
    TextView a, b, c, d, e, f, g, h;
    Button call;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth=FirebaseAuth.getInstance();

        a = (TextView) findViewById(R.id.bn);
        b = (TextView) findViewById(R.id.ed);
        c = (TextView) findViewById(R.id.pb);
        d = (TextView) findViewById(R.id.pr);
        e = (TextView) findViewById(R.id.na);
        f = (TextView) findViewById(R.id.em);
        g = (TextView) findViewById(R.id.ad);
        h = (TextView) findViewById(R.id.co);
        call = (Button) findViewById(R.id.floatingActionButton);
        final String bname = getIntent().getStringExtra("branch_name").toLowerCase();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("m");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(member.class).getBname().toLowerCase().equals(bname)) {
                        Toast.makeText(display.this, "Seller and Book Details", Toast.LENGTH_SHORT).show();
                        String bname = ds.child("bname").getValue().toString();
                        String edi = ds.child("edi").getValue().toString();
                        String pub = ds.child("pub").getValue().toString();
                        String pri = ds.child("pri").getValue().toString();
                        String seller = ds.child("seller").getValue().toString();
                        String ema = ds.child("ema").getValue().toString();
                        String addre = ds.child("addre").getValue().toString();


                        a.setText("" + bname);
                        b.setText("" + edi);
                        c.setText("" + pub);
                        d.setText("₹" + pri);
                        e.setText("" + seller);
                        f.setText("" + ema);
                        g.setText("" + addre);
                        call.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void onClick(View view) {
                                String ph = ds.child("ph").getValue().toString();
                                h.setText("" + ph);
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:" + ph));
                                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                                    return;
                                }
                                startActivity(intent);
                            }
                        });

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
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Toast.makeText(this, "Homepage", Toast.LENGTH_SHORT).show();
            Intent d = new Intent(display.this, option.class);
            startActivity(d);
        }
        if (item.getItemId() == R.id.about) {
            Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
            Intent e = new Intent(display.this, about.class);
            startActivity(e);
        }
        if (item.getItemId() == R.id.log) {
            Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
            finish();

            Intent v = new Intent(display.this, MainActivity.class);
            startActivity(v);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call.performClick();
                }
                break;
        }
    }
}
