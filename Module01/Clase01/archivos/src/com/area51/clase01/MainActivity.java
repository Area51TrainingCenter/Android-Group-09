package com.area51.clase01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText txt_texto;
	Button btn_agrega;
	TextView texto;
	static String cadena = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Traemos la vista(xml)
		setContentView(R.layout.activity_main);

		// Relacionamos variables
		txt_texto = (EditText) findViewById(R.id.txt_texto);
		btn_agrega = (Button) findViewById(R.id.btn_agrega);
		texto = (TextView) findViewById(R.id.texto);

		// ================================================
		// Agregar un botón por programación
		Button boton = new Button(getApplicationContext());

		// Instanciamos el evento clic por programación
		boton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		// addContentView(boton, params);

		if( savedInstanceState != null ){
			texto.setText(cadena);
		}
		
	}

	public void agrega(View view) {

	}

	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	protected void onResume() {
		super.onResume();

		// Implementando clase anónima
		btn_agrega.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!txt_texto.getText().toString().equals("")) {
					
					cadena = cadena + " \n " + txt_texto.getText().toString();
					
					//Agregamos el texto al TextView
					texto.setText( cadena );
					txt_texto.setText("");

				} else {

					Toast.makeText(
							getApplicationContext(), 
							getResources().getString(R.string.error_text)  ,
							Toast.LENGTH_SHORT)
							.show();

				}

			}
		});

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
