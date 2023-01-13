package com.example.sql_lite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String database_name = "studenet.db" ;
    public static final String table_name = "studenet_table" ;
    public static final String col_1 = "ID" ;
    public static final String col_2 = "name" ;
    public static final String col_3 = "surname" ;
    public static final String col_4 = "marks" ;
    public DatabaseHelper(Context context) {
        //super(context, name, factory, version);
        //change super to the following
        super(context, database_name, null, 1);

        //this will create a database
// after creation we nned to comment this
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //time to run query
        // note double quotes should contain space before and after query
        db.execSQL(" CREATE TABLE " + table_name+ " (id INTEGER PRIMARY KEY AUTOINCREMENT,name, surname TEXT, marks INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public boolean insertData(String name,String surname ,String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,surname);
        contentValues.put(col_4,marks);
        long result = db.insert(table_name,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor fetchData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" SELECT * FROM " + table_name,null);
        return res;

    }

    public boolean updateData(String id,String name,String surname,String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,id);
        contentValues.put(col_2,name);
        contentValues.put(col_3,surname);
        contentValues.put(col_4,marks);
        db.update(table_name,contentValues," ID = ? ", new String[] { id });
        return true;


    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(table_name," ID = ? ",new String[] {id});

    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public Cursor distictvalues(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery(" select distinct marks FROM " +table_name,null);
        return  res;
    }
}
