package com.example.practica;

import java.util.ArrayList;

import com.example.adapters.BanderaAdapter;
import com.example.models.BanderaModel;
import com.example.utils.Constantes;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class BanderaActivity extends Activity {

	BanderaAdapter adapter;
	GridView grillaBandera;
	ArrayList<BanderaModel> arregloBandera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bandera);

		grillaBandera = (GridView) findViewById(R.id.grillaBandera);

	}

	@Override
	protected void onResume() {

		super.onResume();

		if (arregloBandera == null) {
			arregloBandera = new ArrayList<BanderaModel>();

			BanderaModel bm = new BanderaModel();
			bm.setImgBandera(R.drawable.b1);
			arregloBandera.add(bm);

			BanderaModel bm2 = new BanderaModel();
			bm2.setImgBandera(R.drawable.b2);
			arregloBandera.add(bm2);

			BanderaModel bm3 = new BanderaModel();
			bm3.setImgBandera(R.drawable.b1);
			arregloBandera.add(bm3);

			BanderaModel bm4 = new BanderaModel();
			bm4.setImgBandera(R.drawable.b2);
			arregloBandera.add(bm4);

		}

		
		adapter = new BanderaAdapter(this, arregloBandera);

		grillaBandera.setAdapter(adapter);
		
		
		grillaBandera.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				Constantes.imagen = arregloBandera.get(position).getImgBandera();
				finish();
				
			}
		});
		
		

	}

}
