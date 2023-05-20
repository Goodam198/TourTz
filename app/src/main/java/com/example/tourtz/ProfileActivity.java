package com.example.tourtz;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    // creation of variables which holds the texts and buttons in login page
    EditText fname, lname, nation, phone, email, password,confirm;
    Button profile_view, profile_update;

    // creation of a variable to hold our database
    DatabaseHelper DB;



    // code for capturing our inserted data to fetch from  the database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        // the inserted data to be fetched from the database on login
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        nation = findViewById(R.id.nation);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        profile_view = findViewById(R.id.profile_view);
        profile_update = findViewById(R.id.profile_update);
        DB = new DatabaseHelper(this);
        viewAll();
        UpdateData();



        profile_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailAddress = email.getText().toString();

                if (TextUtils.isEmpty(emailAddress) )
                    Toast.makeText(ProfileActivity.this, "Email is required to update preferred data", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void UpdateData() {
        profile_update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = DB.updateData(fname.getText().toString(),
                                lname.getText().toString(),
                                nation.getText().toString(),
                                phone.getText().toString(),
                                email.getText().toString(),
                                password.getText().toString(),
                                confirm.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(ProfileActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ProfileActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        profile_view .setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = DB.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("First Name :" + res.getString(1) + "\n");
                            buffer.append("Last Name:" + res.getString(2) + "\n");
                            buffer.append("Nationality:" + res.getString(3) + "\n");
                            buffer.append("Phone Number :" + res.getString(4) + "\n");
                            buffer.append("Email :" + res.getString(5) + "\n");
                            buffer.append("Password :" + res.getString(6) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
