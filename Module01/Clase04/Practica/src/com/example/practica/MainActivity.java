package com.example.practica;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.adapters.EquipoAdapter;
import com.example.models.EquipoModel;
import com.example.utils.Constantes;

public class MainActivity extends Activity {

	EquipoAdapter adapter;
	ListView lstEquipos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lstEquipos = (ListView) findViewById(R.id.lstEquipos);

	}

	@Override
	protected void onResume() {
		super.onResume();

		if (Constantes.arreglo == null) {
			// Inicializamos
			Constantes.arreglo = new ArrayList<EquipoModel>();
		}

		adapter = new EquipoAdapter(this, Constantes.arreglo);

		lstEquipos.setAdapter(adapter);

	}

	public void AgregaEquipo(View view) {

		Intent intent = new Intent(MainActivity.this, AgregaActivity.class);
		startActivity(intent);

	}

}
