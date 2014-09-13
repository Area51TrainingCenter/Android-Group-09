package com.area51.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.area51.util.Constantes;

public class Helper extends SQLiteOpenHelper {

	String TAG = "Helper";

	public Helper(Context context) {

		super(context, Constantes.DB_NAME, null, Constantes.DB_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		Log.d(TAG, "create: " + Constantes.CREATE_TABLE);

		db.execSQL(Constantes.CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL(Constantes.DROP_TABLE);

	}

}
