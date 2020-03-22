package com.example.ticketbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelperClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "booking.db";
    public static final String TABLE_NAME = "booking_table";
    public static final String AUTO_ID = "id";
    public static final String AUTO_NAME = "name";
    public static final String Source = "source";
    public static final String Destination = "destination";
    public static final String Dated = "date";
    public static final String Timed = "time";

    public DatabaseHelperClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (id varchar(30) primary key, name text, source text, destination text,date text,time text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String id, String name, String source,String destination,String date,String time){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = -1;
        ContentValues contentValues = new ContentValues();
        if(id!="") {
            contentValues.put(AUTO_ID, id);
            contentValues.put(AUTO_NAME, name);
            contentValues.put(Source, source);
            contentValues.put(Destination, destination);
            contentValues.put(Dated, date);
            contentValues.put(Timed, time);
            result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        }
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return cursor;
    }

    public boolean updateDataThroughId(String id, String name, String source,String destination,String date,String time){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME+" where id = ? ",new String[] {id});
        //System.out.println(cursor.getCount());

        if(cursor.getCount() == 1)
        {
            //System.out.println("fetched!!"); //It prints in LogCat
            cursor.moveToNext();
            //System.out.println("Name "+cursor.getString(1));
            if(cursor.getString(1).equals(name) && cursor.getString(2).equals(source) && cursor.getString(3).equals(destination) && cursor.getString(4).equals(date) && cursor.getString(5).equals(time))
                return false;
        }


        ContentValues contentValues = new ContentValues();
        contentValues.put(AUTO_ID,id);
        contentValues.put(AUTO_NAME,name);
        contentValues.put(Source,source);
        contentValues.put(Destination,destination);
        contentValues.put(Dated,date);
        contentValues.put(Timed,time);
        int c =  sqLiteDatabase.update(TABLE_NAME,contentValues, " id = ? ", new String[] { id });
        //It returns number of rows updated
        if(c>0)
            return true;
        else
            return false;
    }

    public Integer deleteDataThroughId(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"id = ? ",new String[] { id });
    }
}