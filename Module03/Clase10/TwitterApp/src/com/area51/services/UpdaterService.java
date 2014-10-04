package com.area51.services;

import java.util.ArrayList;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.area51.application.TwitterApplication;
import com.area51.async.ManageTweet;
import com.area51.models.TweetModel;
import com.area51.sqlite.DBHelper;
import com.area51.sqlite.DBOperations;
import com.area51.utils.ConstantsApp;
import com.area51.utils.NetworkApp;

public class UpdaterService extends Service {

	
	private final String TAG = UpdaterService.class.getSimpleName();
	
	static final int DELAY = 3600000;
	private boolean runFlag = false;
	private Updater updater;
	
	private TwitterApplication application;
	
	private DBOperations dbOperations;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		application = (TwitterApplication) getApplication();
		updater = new Updater();
		dbOperations = new DBOperations(this);
		Log.d( ConstantsApp.TAG_APP , TAG + "onCreated");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		
		runFlag = false;
		application.setServiceRunningFlag(false);
		updater.interrupt();
		updater = null;
		
		Log.d( ConstantsApp.TAG_APP , TAG + "onDestroyed");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		
		if(!runFlag){
			runFlag = true;
			application.setServiceRunningFlag(true);
			updater.start();
		}
		
		Log.d( ConstantsApp.TAG_APP , TAG + "onStarted");
		return START_STICKY;
		
	}
	
	/**
	 * Thread that performs the actual update from twitter online service
	 */
	private class Updater extends Thread{
		
		private ArrayList<TweetModel> timeline = new ArrayList<TweetModel>();
		
		public Updater(){
			super("UpdaterService-UpdaterThread");
		}
		
		@Override
		public void run() {
			UpdaterService updaterService = UpdaterService.this;
			while (updaterService.runFlag) {
				Log.d( ConstantsApp.TAG_APP , TAG + "UpdaterThread running");
				try{
					
					if( new NetworkApp( getApplicationContext() ).getNetwork()  ){
						
						timeline = ManageTweet.getHashtag( ConstantsApp.TWITTER_TERM  );
						
						ContentValues values = new ContentValues();
						for(TweetModel tweet : timeline){
							values.clear();
							values.put(DBHelper.C_ID, tweet.getId());
							values.put(DBHelper.C_NAME, tweet.getUsername());
							values.put(DBHelper.C_SCREEN_NAME, tweet.getEmail());
							values.put(DBHelper.C_IMAGE_PROFILE_URL, tweet.getPathImage());
							values.put(DBHelper.C_TEXT, tweet.getDetail());						
							
							dbOperations.insertOrIgnore(values);
						}
					}					
					
					Thread.sleep(DELAY); 
				}catch(InterruptedException e){
					updaterService.runFlag = false;
					application.setServiceRunningFlag(true);
				}
				
			}
		}
	
	} 

}
