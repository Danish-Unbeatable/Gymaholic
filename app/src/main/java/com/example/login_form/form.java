package com.example.login_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;

public class  form extends AppCompatActivity {
    Button submit;
    EditText bookname,publication,edition,price,branch,sellername,phno,email,address;
    member m;
    DatabaseReference reff;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        firebaseAuth= FirebaseAuth.getInstance();

        submit=(Button)findViewById(R.id.sell);
        bookname=(EditText) findViewById(R.id.book_name);
        publication=(EditText) findViewById(R.id.publication);
        edition=(EditText) findViewById(R.id.edition);
        price=(EditText) findViewById(R.id.price);
        branch=(EditText) findViewById(R.id.branch);
        sellername=(EditText) findViewById(R.id.name_seller);
        phno=(EditText) findViewById(R.id.phno);
        email=(EditText) findViewById(R.id.email);
        address=(EditText) findViewById(R.id.address);
        m=new com.example.login_form.member();
        reff= FirebaseDatabase.getInstance().getReference().child("m");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Bookname = bookname.getText().toString().trim();
                if(TextUtils.isEmpty(Bookname))
                {
                    Toast.makeText(form.this,"Please Enter Book Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                String Edition = edition.getText().toString().trim();
                if(TextUtils.isEmpty(Edition))
                {
                    Toast.makeText(form.this,"Please Enter Edition of your book",Toast.LENGTH_SHORT).show();
                    return;
                }
                String Publication = publication.getText().toString().trim();
                if(TextUtils.isEmpty(Publication))
                {
                    Toast.makeText(form.this,"Please Enter Publication",Toast.LENGTH_SHORT).show();
                    return;
                }
                String Branch = branch.getText().toString().trim();
                if(TextUtils.isEmpty(Branch))
                {
                    Toast.makeText(form.this,"Please Enter Branch",Toast.LENGTH_SHORT).show();
                    Toast.makeText(form.this,"Please Enter full branch name",Toast.LENGTH_SHORT).show();
                    return;
                }
                String Price = price.getText().toString().trim();
                if(TextUtils.isEmpty(Price))
                {
                    Toast.makeText(form.this,"Please Enter Price",Toast.LENGTH_SHORT).show();
                    return;
                }
                String Name = sellername.getText().toString().trim();
                if(TextUtils.isEmpty(Name))
                {
                    Toast.makeText(form.this,"Please Enter your Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                String Email = email.getText().toString().trim();
                if(TextUtils.isEmpty(Email))
                {
                    Toast.makeText(form.this,"Please Enter Email Id",Toast.LENGTH_SHORT).show();
                    return;
                }
                String Address = address.getText().toString().trim();
                if(TextUtils.isEmpty(Address))
                {
                    Toast.makeText(form.this,"Please Enter your address details",Toast.LENGTH_SHORT).show();
                    return;
                }
                String Contact = phno.getText().toString().trim();
                if(TextUtils.isEmpty(Contact))
                {
                    Toast.makeText(form.this,"Please Enter your contact details",Toast.LENGTH_SHORT).show();
                    return;
                }


                m.setBname(bookname.getText().toString().trim());
                m.setPub(publication.getText().toString().trim());
                m.setBran(branch.getText().toString().trim());
                m.setSeller(sellername.getText().toString().trim());
                m.setEma(email.getText().toString().trim());
                m.setAddre(address.getText().toString().trim());
                int Edi=Integer.parseInt(edition.getText().toString().trim());
                m.setEdi(Edi);
                long Ph=Long.parseLong(phno.getText().toString().trim());
                m.setPh(Ph);
                double Pri=Double.parseDouble(price.getText().toString().trim());
                m.setPri(Pri);
                reff.push().setValue(m);
                Toast.makeText(form.this, "Data inserted successfully", Toast.LENGTH_LONG).show();

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
            Intent l=new Intent(form.this,option.class);
            startActivity(l);
        }
        if(item.getItemId()==R.id.about){
            Toast.makeText(this,"About us",Toast.LENGTH_SHORT).show();
            Intent e=new Intent(form.this,about.class);
            startActivity(e);
        }
        if(item.getItemId()==R.id.log){
            Toast.makeText(this,"Log Out",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
            finish();

            Intent v=new Intent(form.this,MainActivity.class);
            startActivity(v);
        }
        return super.onOptionsItemSelected(item);
    }
}
