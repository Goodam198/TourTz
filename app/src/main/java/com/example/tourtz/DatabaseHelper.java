package com.example.tourtz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


// creation of database helper to manage our databases
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database declaration
    public static final String DATABASE_NAME = "tourTz.db";
    public DatabaseHelper(Context context) {
        super(context, "tourTz.db", null, 1);
    }

    // code for creating the table columns
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table visitors(visitor_id INTEGER PRIMARY KEY AUTOINCREMENT,fname TEXT,lname TEXT,nation TEXT,phone INTEGER,email TEXT,password TEXT,confirm TEXT)");

    }

    // code for updating table info on upgrading databases
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists visitors");

    }

    // code for inserting data to the database table
    public Boolean insertData(String fname, String lname, String nation, String phone, String email, String password, String confirm) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // values to be inserted to the database table
        values.put("fname", fname);
        values.put("lname", lname);
        values.put("nation", nation);
        values.put("phone", phone);
        values.put("email", email);
        values.put("password", password);
        values.put("confirm", confirm);

        // code for checking if the data is inserted
        long result = sqLiteDatabase.insert("visitors", null, values);
        if (result == -1)
            return false;
        else
            return true;

    }

    // code for reading data from the databases
    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from visitors",null);
        return res;
    }

   // code for updating data in the databases
    public boolean updateData(String fname, String lname, String nation, String phone, String email, String password , String confirm) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fname", fname);
        values.put("lname", lname);
        values.put("nation", nation);
        values.put("phone", phone);
        values.put("email", email);
        values.put("password", password);
        values.put("confirm", confirm);
        sqLiteDatabase.update("visitors", values, "email = ?",new String[] { email });
        return true;
    }





    // code for authenticating user's email
    public Boolean checkEmail(String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from visitors where email = ? ",new String[] {email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


    // code for authenticating user's email and password
    public Boolean checkEmailPassword(String email,String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from visitors where email = ? and password = ? ",new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


}