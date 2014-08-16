package com.area51.appaprendiendo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LauncherActivity extends Activity {

	Handler handler;
	int delay = 4000; //4s

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
	}

	@Override
	protected void onResume() {
		super.onResume();

		handler = new Handler();
		handler.postDelayed(hilo, delay);

	}

	@Override
	protected void onPause() {
		super.onPause();
		
		handler.removeCallbacks(hilo);
		
	};
	
	protected void onDestroy() {
		super.onDestroy();
		
		handler.removeCallbacks(hilo);
		
	};

	Runnable hilo = new Runnable() {

		@Override
		public void run() {
			cambiaActivity();
		}
	};

	public void cambiaActivity() {

		Intent intent 
		= new Intent(LauncherActivity.this, MainActivity.class);
		
		startActivity(intent);

	}

}
