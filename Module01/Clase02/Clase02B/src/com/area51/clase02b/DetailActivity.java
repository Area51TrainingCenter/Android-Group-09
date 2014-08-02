package com.area51.clase02b;

import com.area51.utils.Constantes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {

	Bundle parametro;

	ImageView detalle_imagen;
	TextView detalle_nombre;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_detail);

		detalle_imagen = (ImageView) findViewById(R.id.detalle_imagen);
		detalle_nombre = (TextView) findViewById(R.id.detalle_nombre);

	}

	@Override
	protected void onResume() {

		super.onResume();

		// Recibimos el parametro

		parametro = getIntent().getExtras();

		int position = parametro.getInt("posicion");

		detalle_imagen
		.setImageResource(
				Constantes.lista.get(position)
				.getRutaInternaImagen()
				);

	}

}
