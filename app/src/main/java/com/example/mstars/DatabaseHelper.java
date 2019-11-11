package com.example.mstars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final String DATABASE_NAME = "mSTARS.db";
    private static  final String TABLE_NAME = "sys_User";
    private static  final String COL_1 = "ID";
    private static  final String COL_2 = "LoginName";
    private static  final String COL_3 = "Password";

    public DatabaseHelper(Context context) {
        super(context, "mSTARS.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table sys_User (ID INTEGER Primary Key AUTOINCREMENT,LoginName TEXT,Password TEXT)");
//        String sql =
//                "INSERT or replace INTO sys_User (LoginName, Password) VALUES('leo','leo123')" ;
//        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table If Exists sys_User");
        ContentValues values = new ContentValues();
        values.put("LoginName", "leo");
        values.put("Password", "1234");
        // insert the row
        db.insert("sys_User", null, values);

    }


    //inserting in database
    public boolean insert (String LoginName, String Password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LoginName",LoginName);
        contentValues.put("Password",Password);
        long ins = db.insert("sys_User",null, contentValues);
        if(ins==-1) return false;
        else return true;
    }
    //checking if username exists
    public Boolean chkusername(String LoginName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from sys_User Where LoginName=?",new String[]{LoginName});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    //checking username and password
    public Boolean usernamepassword(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from sys_User Where LoginName=? and Password=?",new String[]{username,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
