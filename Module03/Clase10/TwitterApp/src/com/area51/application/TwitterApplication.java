package com.area51.application;

import com.area51.services.UpdaterService;
import com.area51.utils.ConstantsApp;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class TwitterApplication extends Application {

	
	private static final String TAG = TwitterApplication.class.getSimpleName();
	private boolean serviceRunningFlag;
	
	public boolean isServiceRunning(){
		return serviceRunningFlag;
	}
	
	public void setServiceRunningFlag(boolean serviceRunningFlag){
		this.serviceRunningFlag = serviceRunningFlag;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d( ConstantsApp.TAG_APP , TAG + "onCreated");
		startService(new Intent(this, UpdaterService.class));
		
		//parse
		
		
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		Log.i(TAG, "onTerminated");
		stopService(new Intent(this, UpdaterService.class));
	}
	
	
}
