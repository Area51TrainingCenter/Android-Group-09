package com.area51.sqlite;

import com.area51.utils.ConstantsApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {


	private static final String TAG = DBHelper.class.getSimpleName();
	
    public static final String DB_NAME = "twitter.db";
    public static final int DB_VERSION = 1;
    
    public static final String TABLE = "hashtag";
    public static final String C_ID = BaseColumns._ID;
    public static final String C_NAME = "name";
    public static final String C_SCREEN_NAME = "screen_name";
    public static final String C_IMAGE_PROFILE_URL = "image_profile_url";
    public static final String C_TEXT = "text";
    
    public static final int C_ID_INDEX = 0;
    public static final int C_NAME_INDEX = 1;
    public static final int C_SCREEN_NAME_INDEX = 2;
    public static final int C_IMAGE_PROFILE_URL_INDEX = 3;
    public static final int C_TEXT_INDEX = 4;
    
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {

        String  sql = "create table " + TABLE + " (" + C_ID + " int primary key, " 
               + C_NAME + " text, " + C_SCREEN_NAME + " text, " +
        		C_IMAGE_PROFILE_URL + " text, " + C_TEXT + " text )";
        db.execSQL(sql);
        Log.d( ConstantsApp.TAG_APP , TAG + " onCreated sql: " + sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE); 
        Log.d( ConstantsApp.TAG_APP , TAG + "onUpdated");
        onCreate(db); 

	}

}
