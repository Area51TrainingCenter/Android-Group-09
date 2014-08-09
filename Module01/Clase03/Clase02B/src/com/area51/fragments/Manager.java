package com.area51.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.area51.utils.Constantes;


public class Manager extends FragmentStatePagerAdapter {

	/*
	ArrayList<ImageModel> arreglo;

	public Manager(FragmentManager fm, ArrayList<ImageModel> arreglo) {
		super(fm);
		this.arreglo = arreglo;
	}
	*/

	public Manager(FragmentManager fm) {
		super(fm);
		
	}

	@Override
	public Fragment getItem(int position) {

		
		ImageFragment ifragment = new ImageFragment();
		Bundle parametro = new Bundle();
		
		//Malo malo malo
		//parametro.putInt("posicion", position);
		//Good Good 
		parametro.putInt(ifragment.parametro, position);		
		ifragment.setArguments(parametro);
		
		return ifragment;
	}

	@Override
	public int getCount() {

		//Obtenemos la cantidad de elementos a mostrar
		//usando el arreglo 
		//que implementamos como variable de aplicación
		
		Log.d("app","size" + Constantes.lista.size());
		
		return Constantes.lista.size();
		
	}

}
