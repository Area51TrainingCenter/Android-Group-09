package com.area51.appsqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.area51.sqlite.Helper;
import com.area51.util.Constantes;

public class RegistroActivity extends Activity {

	String TAG = "Registro";

	EditText rtxtusuario;
	EditText rtxtclave;

	// Para Sqlite
	Helper helper;
	SQLiteDatabase query;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro);

		rtxtusuario = (EditText) findViewById(R.id.rtxt_usuario);
		rtxtclave = (EditText) findViewById(R.id.rtxt_clave);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		helper = new Helper(this);

	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		Constantes.registro = 0;
	}

	public void ingresaUsuario(View view) {

		String usuario = rtxtusuario.getText().toString();
		String clave = rtxtclave.getText().toString();

		if (!usuario.equals("") && !clave.equals("")) {

			query = helper.getWritableDatabase();

			// Usamos el contentValues para el ingreso de datos
			ContentValues values = new ContentValues();

			values.put(Constantes.C_USER, usuario);
			values.put(Constantes.C_PASSWORD, clave);

			// Hacemos el insert

			if (query.insert(Constantes.DB_TABLE, null, values) > -1) {
				
				Constantes.registro = 1;
				Constantes.usuario =  usuario;
				//Terminamos la actividad
				finish();
				
			}else{
				//Mostramos error de mensaje
			}

		} else {

			Toast.makeText(getApplicationContext(), "Ingrese datos correctos",
					Toast.LENGTH_SHORT).show();

		}

	
	}

}
