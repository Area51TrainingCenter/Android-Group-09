package com.area51.appsqlite;

import com.area51.sqlite.Helper;
import com.area51.util.Constantes;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText txtusuario;
	EditText txtclave;
	Button btningresa;
	
	//Para sqlite
	Helper helper;
	SQLiteDatabase query;
	
	String TAG = "Logueo";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Relacionamos variables con los componentes
		txtusuario = (EditText)findViewById(R.id.txtusuario);
		txtclave = (EditText)findViewById(R.id.txtclave);
		btningresa = (Button)findViewById(R.id.btningresa);
				
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		
		//Inicializamos el helper
		helper = new Helper(this);
				
	}
	
	
	public void Validacion(View view){
		
		String usuario = txtusuario.getText().toString();
		String clave = txtclave.getText().toString();
		
		if( !usuario.equals("") && !clave.equals("") ){
			
			//Abrimos conexion a sqlite			
			query = helper.getReadableDatabase();
			
			String sql = "SELECT " + Constantes.C_USER +
					" FROM " + Constantes.DB_TABLE +
					" WHERE " + Constantes.C_USER + "=" + usuario +  
					" AND " + Constantes.C_PASSWORD + "=" + clave;
			
			Log.d(TAG, "sql: " + sql);
			
			
			
			
			
			
			
			
			
		}else{
			//Mostramos mensaje de error
		}
		
		
	}
	
	
	

}
