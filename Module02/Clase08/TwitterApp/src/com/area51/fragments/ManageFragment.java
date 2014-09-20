package com.area51.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ManageFragment extends FragmentStatePagerAdapter {


	public ManageFragment(FragmentManager fm) {
		super(fm);
		
	}

	@Override
	public Fragment getItem(int position) {

		Fragment fragment = null;
		switch (position) {
		
		case 0:
			fragment = new InicioFragment();
			break;
		case 1:
			fragment = new ActividadFragment();
			break;
		case 2:
			fragment = new DescubreFragment();
			break;
		}

		return fragment;

	}

	@Override
	public int getCount() {
		return 3;
	}

}
