package com.example.pinky.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

/**
 * Created by jihon on 2018-02-20.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "chat.db";
    public static final String TABLE_NAME = "CHAT";
    public static final int VERSION_NUM = 5;
    public static final String KEY_ID = "_id";
    public static final String KEY_MESSAGE = "MESSAGE";
    public static final String ACTIVITY_NAME = "ChatDatabaseHelper";



    public ChatDatabaseHelper(Context ctx){

        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    //create table if database doesn't exist yet, db is the database object
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + "( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_MESSAGE + " TEXT )";
        db.execSQL(createTable) ;

        Log.i(ACTIVITY_NAME, "Calling onCreate");
    }

    //handle upgrading the data, or drop the table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        Log.i(ACTIVITY_NAME, "Calling onUpgrade, oldVersion=" + oldVer+" newVersion=" + newVer);
    }


}