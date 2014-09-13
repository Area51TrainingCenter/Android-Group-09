package com.area51.appsqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.area51.sqlite.Helper;
import com.area51.util.Constantes;

public class MainActivity extends Activity {

	EditText txtusuario;
	EditText txtclave;
	Button btningresa;
	TextView enlace;

	// Para sqlite
	Helper helper;
	SQLiteDatabase query;

	String TAG = "Logueo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Relacionamos variables con los componentes
		txtusuario = (EditText) findViewById(R.id.txtusuario);
		txtclave = (EditText) findViewById(R.id.txtclave);
		btningresa = (Button) findViewById(R.id.btningresa);
		enlace = (TextView)findViewById(R.id.enlace);

	}

	@Override
	protected void onResume() {

		super.onResume();

		// Inicializamos el helper
		helper = new Helper(this);
		
		//VERIFICAMOS LOGUEO
		if( Constantes.registro == 1 ){		
			
			finish();
			
			//Cambiamos a la actividad
			Intent intent 
				= new Intent(MainActivity.this, InicioActivity.class);
			startActivity(intent);
			
		}
		
		
		
		enlace.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Cambiamos a la actividad para registrar un usuario
				Intent intent 
					= new Intent(MainActivity.this, RegistroActivity.class);
				startActivity(intent);
			}
		});

	}


	public void Validacion(View view) {

		String usuario = txtusuario.getText().toString();
		String clave = txtclave.getText().toString();

		if (!usuario.equals("") && !clave.equals("")) {

			// Abrimos conexion a sqlite
			query = helper.getReadableDatabase();

			String sql = "SELECT " + Constantes.C_USER + " FROM "
					+ Constantes.DB_TABLE + " WHERE " + Constantes.C_USER
					+ "='" + usuario + "'" + " AND " + Constantes.C_PASSWORD
					+ "='" + clave + "'";

			Log.d(TAG, "sql: " + sql);

			Cursor cursor = query.rawQuery(sql, null);

			// Obtenemos la cantidad de filas
			// cursor.getCount();

			// query.rawQuery(sql, selectionArgs)
			// query.insert(table, nullColumnHack, values)
			// query.update(table, values, whereClause, whereArgs)
			// query.delete(table, whereClause, whereArgs)

			/*
			 * ContentValues values = new ContentValues(); values.put(
			 * Constantes.C_USER , "Pepe"); values.put( Constantes.C_USER ,
			 * "Pepe"); values.put( Constantes.C_USER , "Pepe"); values.put(
			 * Constantes.C_USER , "Pepe"); query.insert(Constantes.DB_TABLE,
			 * null, values);
			 */

			if (cursor.moveToFirst()) {

				// Guardamos los datos
				// y cambiamos de actividad
				
				//Constantes.usuario = cursor.getColumnIndex(columnName) (Constantes.C_USER_INDEX);
				Constantes.usuario = usuario;
				
				Intent intent 
				= new Intent(MainActivity.this, InicioActivity.class);
			startActivity(intent);
				

			} else {
				// No hay registros
				// Mostramos un mensaje
				Toast.makeText(getApplicationContext(), "Ingrese datos correctos: " + usuario,
						Toast.LENGTH_SHORT).show();

			}

		} else {
			// Mostramos mensaje de error
		}

	}

}
