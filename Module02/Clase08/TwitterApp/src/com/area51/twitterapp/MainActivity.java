package com.area51.twitterapp;

import com.area51.fragments.ManageFragment;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

public class MainActivity extends FragmentActivity 
implements OnPageChangeListener, TabListener {

	ViewPager viewpager;
	ManageFragment adapter;
	ActionBar actionbar;

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
		viewpager.setOnPageChangeListener(this);
		
		//HABILITAMOS LAS PESTAÑAS EN LA PARTE SUPERIOR
		actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		

		actionbar.addTab(
				actionbar.newTab()
				.setText("Inicio")
				.setTabListener(this)
		);
		
		actionbar.addTab(
				actionbar.newTab()
				.setText("Actividad")
				.setTabListener(this)
		);
		
		actionbar.addTab(
				actionbar.newTab()
				.setText("Descubre")
				.setTabListener(this)
		);

		
		
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
		
	}

	@Override
	public void onPageSelected(int position) {
		actionbar.setSelectedNavigationItem(position);
		
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		
		viewpager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
		
	}

}
