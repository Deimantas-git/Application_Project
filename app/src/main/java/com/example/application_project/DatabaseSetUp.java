package com.example.application_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

public class DatabaseSetUp extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "patient_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "patients";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table user(email text primary key, password text)");

        String createPatientsTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT);";
        db.execSQL(createPatientsTableQuery);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists user");

    }

    public DatabaseSetUp(@Nullable Context context){
        super(context, "Login.db", null, 1);
    }
    public boolean insert(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if(ins==-1) return false;
        else return true;
    }


    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email =?", new String[]{email});
        if(cursor.getCount()>0) return true;
        else return false;
    }


    public Boolean checkPassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and password =?", new String[]{email,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }


    public long insertPatient(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_NAME, name);
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }
}
