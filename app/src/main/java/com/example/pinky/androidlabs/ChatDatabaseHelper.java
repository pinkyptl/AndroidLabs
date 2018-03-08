package com.example.pinky.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.ContextMenu;

/**
 * Created by pinky on 2018-03-01.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Message.db";
public static final int VERSION_NUM = 5;
public  static final String TABLE_NAME="myTable";
public  static final String KEY_ID="_id";
public static final String KEY_MESSAGE="message";




    public ChatDatabaseHelper(Context ctx) {
        super(ctx,DATABASE_NAME,  null,VERSION_NUM);
    }
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+TABLE_NAME+ "( "+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
        +KEY_MESSAGE+" TEXT); ");
        Log.i("chatDatabaseHelper","calling OnCreate");
    }
    public  void onUpgrade(SQLiteDatabase db,int oldVer,int newVer)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
        onCreate(db);
        Log.i("ChatDatabaseHelper","Calling on Upgrage,oldVersion="+oldVer+"newVersion+"+newVer);

    }
public  void onOpen(SQLiteDatabase db)
{
    Log.i("Database","database opened");
}
}