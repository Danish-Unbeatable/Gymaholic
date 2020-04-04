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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextconfPassword;
    Button mButtonRegister;
    Button mButtonLogin;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mTextconfPassword = (EditText) findViewById(R.id.edittext_confpassword);
        mButtonRegister = (Button) findViewById(R.id.edittext_Register);
        mButtonLogin = (Button) findViewById(R.id.edittext_login);
        firebaseAuth = FirebaseAuth.getInstance();
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(LoginIntent);

            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = mTextUsername.getText().toString().trim();
                String Password = mTextPassword.getText().toString().trim();
                String confirm = mTextconfPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(RegisterActivity.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(RegisterActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(confirm)) {
                    Toast.makeText(RegisterActivity.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password is too Short", Toast.LENGTH_SHORT).show();
                }
                if(!Password.equals(confirm))
                {
                    Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();

                }
                if (Password.equals(confirm)) {
                    firebaseAuth.createUserWithEmailAndPassword(username, Password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        Toast.makeText(RegisterActivity.this, "Registeragtion Successfull", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                }

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
            Toast.makeText(this,"You haven't Registered to BookWeiser",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.about){
            Toast.makeText(this,"You haven't Registered to BookWeiser",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.log){
            Toast.makeText(this,"You haven't Registered to BookWeiser",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}
