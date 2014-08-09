package com.area51.clase02b;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.area51.fragments.Manager;

public class DetailActivity extends FragmentActivity 
implements OnPageChangeListener {

	Bundle parametro;

	ViewPager viewpager;
	Manager fspaAdapter;
	
	static int position = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		viewpager = (ViewPager) findViewById(R.id.viewpager);
		
		
		if( savedInstanceState == null ){

			// Recibimos el parametro
			parametro = getIntent().getExtras();
			position = parametro.getInt("posicion");
		}


	}

	@Override
	protected void onResume() {

		super.onResume();

		
		//Inicializamos la variable fspaAdapter 
		fspaAdapter = new Manager(getSupportFragmentManager());
		
		
		viewpager.setOnPageChangeListener(this);
				
		
		//Asignamos el adapter
		viewpager.setAdapter(fspaAdapter);
		
		//Enviamos la posicion seleccionada
		viewpager.setCurrentItem(position);

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
		
	}

	@Override
	public void onPageSelected(int newPosition) {
		position = newPosition;
	}

}
