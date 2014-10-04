package com.area51.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.area51.adapters.TweetAdapter;
import com.area51.models.TweetModel;
import com.area51.utils.ConstantsApp;

public class DBOperations {

	private static final String TAG = DBOperations.class.getSimpleName();
	private DBHelper dbHelper;
	private Context context;
	
	public DBOperations(Context context){
		dbHelper = new DBHelper(context);
		this.context = context;
	}
	
	public void insertOrIgnore(ContentValues values){
		Log.d( ConstantsApp.TAG_APP , TAG + "insertOrIgnore on: " + values);
		SQLiteDatabase dataBase = dbHelper.getWritableDatabase();
		try{
			dataBase.insertWithOnConflict(DBHelper.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		}finally{
			dataBase.close();
		}
	}
	
	public TweetAdapter getStatusUpdates(){

		TweetAdapter adapter = new TweetAdapter(context);
		
		SQLiteDatabase dataBase = dbHelper.getReadableDatabase();
		Cursor cursor = dataBase.query(DBHelper.TABLE, null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			while (cursor.isAfterLast() == false) {
				TweetModel tweet = new TweetModel();
				tweet.setId(String.valueOf(cursor.getInt(DBHelper.C_ID_INDEX)));
				tweet.setUsername(cursor.getString(DBHelper.C_NAME_INDEX));
				tweet.setEmail(cursor.getString(DBHelper.C_SCREEN_NAME_INDEX));
				tweet.setPathImage(cursor.getString(DBHelper.C_IMAGE_PROFILE_URL_INDEX));
				tweet.setDetail(cursor.getString(DBHelper.C_TEXT_INDEX));
				adapter.add(tweet);
				cursor.moveToNext();
								
				
			}
		}
		
		return adapter;
	}
	
}
