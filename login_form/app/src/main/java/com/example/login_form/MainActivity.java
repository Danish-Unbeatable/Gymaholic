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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    Button mButtoncontactus;
    TextView mTextViewRegister;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextUsername=(EditText)findViewById(R.id.edittext_username);
        mTextPassword=(EditText)findViewById(R.id.edittext_password);
        mButtonLogin=(Button)findViewById(R.id.edittext_login);
        mButtoncontactus=(Button)findViewById(R.id.contactus);
        mTextViewRegister=(TextView)findViewById(R.id.edittext_register);
        firebaseAuth = FirebaseAuth.getInstance();

        /*if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this, option.class));
            finish();
        }*/

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RegisterIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(RegisterIntent);
            }
        });
        mButtoncontactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,contact.class);
                startActivity(i);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mTextUsername.getText().toString().trim();
                String Password=mTextPassword.getText().toString().trim();
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(MainActivity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    Toast.makeText(MainActivity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Password.length()<6)
                {
                    Toast.makeText(MainActivity.this,"Password is too Short",Toast.LENGTH_SHORT).show();
                }
                firebaseAuth.signInWithEmailAndPassword(username, Password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent o=new Intent(MainActivity.this,option.class);
                                    startActivity(o);
                                    Toast.makeText(MainActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(MainActivity.this,"Login Error Invalid username or password",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


































        mButtoncontactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencontactus();
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
            Toast.makeText(this,"You haven't Logged In to BookWeiser",Toast.LENGTH_LONG).show();
        }
        if(item.getItemId()==R.id.about){
            Toast.makeText(this,"You haven't Logged In to BookWeiser",Toast.LENGTH_LONG).show();
        }
        if(item.getItemId()==R.id.log){
            Toast.makeText(this,"You haven't Logged In to BookWeiser",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void opencontactus() {
    Intent intent=new Intent(this,contact.class);
            startActivity(intent);
    }
}
