package com.Prince.forensicmart_admin.LocalDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String database_name = "autoLogin.db" ;
    public static final String table_name = "info" ;
    public static final String col_1 = "uid" ;
    public static final String col_2 = "password" ;

    public DataBaseHelper(@Nullable Context context) {
        super(context,database_name,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE " + table_name+ " (uid text, password TEXT, designation text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public boolean insertData(String uid,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,uid);
        contentValues.put(col_2,password);
        long result = db.insert(table_name,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }


    public boolean deleteData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ table_name);
        return true;
    }

    public Cursor distictvalues(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery(" select distinct designation FROM " +table_name+ " LIMIT 1 ",null);
        return  res;
    }


    public Cursor fetchData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res2 = db.rawQuery(" SELECT * FROM " + table_name,null);
        return res2;

    }
}
