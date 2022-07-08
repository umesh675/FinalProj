package com.example.cropandroidapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    EditText name,username,password,repassword, phoneNo;
    Button signup,signin;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        phoneNo = findViewById(R.id.phoneNo);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signup = findViewById(R.id.btnsignup);
        signin = findViewById(R.id.btnsignin);

        db = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString();
                String username1 = username.getText().toString();
                String password1 = password.getText().toString();
                String repassword1 = repassword.getText().toString();
                String phoneNo1 = phoneNo.getText().toString();

                if(name1.isEmpty()){
                    name.setError("Please Enter name");
                }
                else if(username1.isEmpty()){
                    username.setError("Please Enter User Name");
                }
                else if(db.checkUsername(username1)){
                    username.setError("This type of user already exist");
                }
                else if(password1.isEmpty()){
                    password.setError("Please Enter Password");
                }
                else if(repassword1.isEmpty()){
                    repassword.setError("Please Enter Repassword");
                }
                else if(!repassword1.equals(password1)){
                    repassword.setError("Password are not similar");
                }
                else if(phoneNo1.isEmpty()){
                    phoneNo.setError("Please Enter PhoneNo");
                }
                else if(phoneNo1.length() != 10){
                    phoneNo.setError("Phone Should be have 10 digit");
                }
                else{

                    Boolean insert = db.insertUser(name1,username1,password1,phoneNo1);

                    if(insert){
                        Toast.makeText( RegisterActivity.this," Registration Successfully",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

                        startActivity(intent);
                    }
                    else{
                        Toast.makeText( RegisterActivity.this,"Some Error In Registration",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}