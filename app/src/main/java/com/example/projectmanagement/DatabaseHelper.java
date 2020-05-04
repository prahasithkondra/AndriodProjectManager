package com.example.projectmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.security.PrivateKey;
import java.sql.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "Project_info";
    private static final String COL1 = "ID";
    private static final String COL2 = "project_name";
    private static final String COL3 = "course_name";
    private static final String COL4 = "course_number";
    private static final String COL5 = "Instructor_name";
    private static final String COL6 = "project_number";
    private static final String COL7 = "project_Desc";
    private static final String COL8 = "due_date";



    public DatabaseHelper(@Nullable Context context) {
        super(context,TABLE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, project_name TEXT NOT NULL, course_name TEXT NOT NULL, course_number INTEGER, Instructor_name TEXT, project_number INTEGER, project_Desc TEXT, due_date Date NOT NULL)";
        db.execSQL( createTable );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS "+ TABLE_NAME );
        onCreate( db );
    }

    public boolean addData(String project_name, String course_name, int course_number, String Instructor_name, int project_number, String project_Desc, String due_date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(  );
        contentValues.put( COL2, project_name);
        contentValues.put( COL3, course_name);
        contentValues.put( COL4, course_number);
        contentValues.put( COL5, Instructor_name);
        contentValues.put( COL6, project_number);
        contentValues.put( COL7, project_Desc);
        contentValues.put( COL8, due_date.toString());


        Log.d( TAG, "addData: Adding data to" + TABLE_NAME);
        long result = db.insert( TABLE_NAME,null, contentValues );

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }


    public long deleteProjectItem(int id)
    {
//        ,String projectname, String courseName, String dueDate
        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "DELETE  FROM "+ TABLE_NAME +"WHERE ID=="+ id;
//        Cursor data = db.rawQuery( query,null );
         return db.delete(TABLE_NAME,"ID=?",new String[]{String.valueOf(id)});
    }
    public Cursor getData(){
       SQLiteDatabase db = this.getWritableDatabase();
       String query = "SELECT * FROM "+ TABLE_NAME;
       Cursor data = db.rawQuery( query,null );
       return data;
    }

}
