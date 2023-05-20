package com.example.tourtz;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    // creation of variables which holds the texts and buttons in registration page
    EditText fname, lname, nation, phone, email, password, confirm;
    Button register;
    CheckBox agree;

    // creation of a variable to hold our database
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // the inserted data to be captured
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        nation = findViewById(R.id.nation);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);


        // capturing buttons
        register = (Button) findViewById(R.id.register);




        // capturing the checkbox
        agree = findViewById(R.id.agree);

        // calling the database for above data to be inserted
        DB = new DatabaseHelper(this);


        // code for the register button to insert the data on clicking it
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = fname.getText().toString();
                String lastName = lname.getText().toString();
                String Nationality = nation.getText().toString();
                String phoneNumber = phone.getText().toString();
                String emailAddress = email.getText().toString();
                String pass = password.getText().toString();
                String passConfirm = confirm.getText().toString();


                // code for validating the inserted data

                if(TextUtils.isEmpty(firstName) ||TextUtils.isEmpty(lastName) || TextUtils.isEmpty(Nationality) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(passConfirm) || !agree.isChecked() )
                    Toast.makeText(RegistrationActivity.this, "Fill all the fields to proceed", Toast.LENGTH_SHORT).show();

                else {
                    if (pass.equals(passConfirm)){
                        Boolean checkuser = DB.checkEmail(emailAddress);
                        if (checkuser == false){
                            Boolean insert = DB.insertData(firstName,lastName,Nationality,phoneNumber,emailAddress,pass,passConfirm);
                            if (insert == true){
                                Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegistrationActivity.this, "User Exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegistrationActivity.this, "Password not Matching", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


    }
}