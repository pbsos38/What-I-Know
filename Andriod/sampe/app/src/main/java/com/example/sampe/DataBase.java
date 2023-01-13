package com.example.sampe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DataBase extends SQLiteOpenHelper {
    public static final String database_name = "training.db" ;
    public static final String table_name = "users" ;
    public static final String col_1 = "Name" ;
    public static final String col_2 = "Age" ;
    public static final String col_3 = "Mobile" ;
    public static final String col_4 = "Addr" ;

    public DataBase(@Nullable Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + table_name+ " (Name text, Age TEXT, Mobile text, Addr text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }
    public boolean insertData(String Name,String Age ,String Mobile,String Addr)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,Name);
        contentValues.put(col_2,Age);
        contentValues.put(col_3,Mobile);
        contentValues.put(col_4,Addr);
        long result = db.insert(table_name,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor fetchData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res2 = db.rawQuery(" SELECT * from " + table_name ,null);
        return res2;

        /*+ " where Name='"+name+"'"*/
    }
}
