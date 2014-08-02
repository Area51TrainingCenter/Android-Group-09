package com.area51.clase02a;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.area51.adapters.PersonaAdapter;
import com.area51.models.PersonaModel;

public class MainActivity extends Activity {

	EditText txt_nombre;
	Button btn_agrega;
	ListView lst_nombres;

	static ArrayList<PersonaModel> lista;
	PersonaAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Hacemos la relación con el diseño
		txt_nombre = (EditText) findViewById(R.id.txt_nombre);
		btn_agrega = (Button) findViewById(R.id.btn_agrega);
		lst_nombres = (ListView) findViewById(R.id.lst_nombres);

	}

	@Override
	protected void onResume() {

		super.onResume();
		// Inicializamos el arrayList
		if (lista == null) {
			lista = new ArrayList<PersonaModel>();

			/*
			 * for (int i = 0; i < 2000; i++) {
			 * 
			 * // Ponemos un dato 
			 * PersonaModel pm = new PersonaModel();
			 * pm.setNombrePersona("Segundo " + i); lista.add(pm); }
			 */

		}

		// Inicializamos el adapter
		adapter = new PersonaAdapter(lista, this);

		// Asignar el adapter al listview
		lst_nombres.setAdapter(adapter);

		// Seteamos el evento clic dle botón
		btn_agrega.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				additem();

			}
		});

	}//fin del onresume()

	public void additem() {

		if (!txt_nombre.getText().toString().equals("")) {
			
			PersonaModel pm = new PersonaModel();
			
			pm.setIdPersona( lista.size() );
			pm.setNombrePersona( txt_nombre.getText().toString() );
			
			//Agregamos el elemento al ArrayList
			lista.add(pm);
			
			//limpiamos el editext
			txt_nombre.setText("");
			
			//Avisamos al adapter que hubo un cambio 
			//Y lo vuelve a cargar
			adapter.notifyDataSetChanged();						

		}else{
			//Mostramos mensaje de error
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
