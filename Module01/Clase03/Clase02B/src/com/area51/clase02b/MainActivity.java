package com.area51.clase02b;

import java.util.ArrayList;

import com.area51.adapters.ImageAdapter;
import com.area51.models.ImageModel;
import com.area51.utils.Constantes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {

	GridView grilla;
	ImageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		grilla = (GridView) findViewById(R.id.grilla);
	}

	@Override
	protected void onResume() {

		super.onResume();

		if (Constantes.lista == null) {

			Constantes.lista = new ArrayList<ImageModel>();

			for (int i = 0; i <= 10; i++) {

				ImageModel im = new ImageModel();

				// Seteamos imagenes obtenidas desde la aplicacion
				// im.setRutaInternaImagen(R.drawable.sample_0);
				
				// Seteamos imagenes obtenidas de internet
				im.setRutaExternaImagen
				(Constantes.API_IMAGE + "sample_" + i + ".jpg");
				
				Constantes.lista.add(im);

			}

		}

		adapter = new ImageAdapter(this, Constantes.lista);
		grilla.setAdapter(adapter);

		// Instanciamos el evento clic
		// De los elementos que están dentro
		// de grilla(gridview)
		grilla.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {

				// Cambiamos de actividad

				// Creamos un intent
				Intent intent = new Intent(MainActivity.this,
						DetailActivity.class);
				// Usamos un bundle para asignarle parametros
				// que recibiremos en la otra actividad
				Bundle bundle = new Bundle();
				bundle.putInt("posicion", position);
				// Le asignamos los parametros al intent
				intent.putExtras(bundle);
				// Iniciamos la el cambio de actividad
				startActivity(intent);
			}

		});

	}

}
