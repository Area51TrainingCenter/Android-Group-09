package com.example.practica;

import com.example.models.EquipoModel;
import com.example.utils.Constantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class AgregaActivity extends Activity {

	EditText txttitulo;
	EditText txtdetalle;
	ImageView imgElegir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agrega);

		txttitulo = (EditText) findViewById(R.id.txttitulo);
		txtdetalle = (EditText) findViewById(R.id.txtdetalle);
		imgElegir = (ImageView) findViewById(R.id.imgElegir);

	}

	@Override
	protected void onResume() {

		super.onResume();

		// mostramos la imagen escogida

		if (Constantes.imagen != 0) {
			imgElegir.setImageResource(Constantes.imagen);
		}

		// Mostramos el titulo que fue ingresado

		if (!Constantes.titulo.equals("")) {
			txttitulo.setText(Constantes.titulo);
		}
		
		// Mostramos el detalle que fue ingresado
		if (!Constantes.detalle.equals("")) {
			txtdetalle.setText(Constantes.detalle);
		}

		imgElegir.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String titulo = txttitulo.getText().toString();
				String detalle = txtdetalle.getText().toString();

				if (!titulo.equals("")) {
					Constantes.titulo = titulo;
				}

				if (!detalle.equals("")) {
					Constantes.detalle = detalle;
				}

				Intent intent = new Intent(AgregaActivity.this,
						BanderaActivity.class);
				startActivity(intent);

			}
		});

	}

	public void GrabaEquipo(View view) {

		String titulo = txttitulo.getText().toString();
		String detalle = txtdetalle.getText().toString();

		if (!titulo.equals("") && !detalle.equals("")) {

			EquipoModel em = new EquipoModel();

			if (Constantes.imagen == 0) {
				em.setImgEquipo(R.drawable.ic_launcher);
			} else {
				em.setImgEquipo(Constantes.imagen);
			}

			em.setNombreEquipo(titulo);
			em.setDetalleEquipo(detalle);

			Constantes.arreglo.add(em);

			// Limpiamos variables
			Constantes.imagen = 0;
			Constantes.titulo = "";
			Constantes.detalle = "";

			// Terminamos la vida de la actividad
			finish();

		} else {
			// mostramos mensaje de error
		}

	}

}
