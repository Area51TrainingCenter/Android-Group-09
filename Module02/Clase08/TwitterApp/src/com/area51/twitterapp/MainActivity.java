package com.area51.twitterapp;

import com.area51.fragments.ManageFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	ViewPager viewpager;
	ManageFragment adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		viewpager = (ViewPager) findViewById(R.id.viewpager);

	}

	@Override
	protected void onResume() {

		super.onResume();

		adapter 
		= new ManageFragment(getSupportFragmentManager());
		
		viewpager.setAdapter(adapter);
		

	}

}
