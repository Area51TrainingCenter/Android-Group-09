package com.area51.appws;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.area51.adapters.UsuariosAdapter;
import com.area51.models.UsuariosModel;
import com.area51.utils.Constantes;
import com.area51.utils.RESTClient;

public class MainActivity extends Activity {

	// Variables de clase
	ListView lista;
	UsuariosAdapter adapter;
	Context context;
	ArrayList<UsuariosModel> listaUsuarios;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lista = (ListView) findViewById(R.id.lista);

	}

	@Override
	protected void onResume() {

		super.onResume();

		//Guardamos el contexto en el context
		context = this;
		//Inicializamos el ArrayList
		listaUsuarios = new ArrayList<UsuariosModel>();
		

		String url = Constantes.API + Constantes.API_VERSION
				+ Constantes.API_SECTION;

		new Usuarios().execute(url);

	}
	
	public void muestraDatos(String json){
		
		try {
			
			JSONObject data = new JSONObject(json);
			
			int total = data.getInt("total");
			
			if(total > 0){
				
				//Obtenemos el jsonArray
				JSONArray arreglo = data.getJSONArray("data");
				
				JSONObject item = null;
				UsuariosModel um;
				
				for (int i = 0; i < total; i++) {
					//Obtenemos el jsonobject
					item =  (JSONObject) arreglo.get(i);					
					//item = arreglo.getJSONObject(i);					
					
					//Instanciamos el modelo					
					um = new UsuariosModel();
					
					um.setCorreo( item.getString("correo") );
					um.setNombre( item.getString("nombre"));
					um.setRegistro(item.getString("registro"));
					um.setIdusuario(item.getString("idusuario"));				
					
					//Agregamos al arreglo
					listaUsuarios.add(um);
					
				}
				
				adapter = new UsuariosAdapter(context, listaUsuarios);
				lista.setAdapter(adapter);
				
				
				
			}else{
				//Mensaje de no hay datos a mostrar
			}
			
			/*
			if( data.getJSONObject("response").toString()
					== "OK"){
				
			}*/
			
			
			
			
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	

	class Usuarios extends AsyncTask<String, Void, String> {

		private ProgressDialog mensaje;

		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			mensaje = new ProgressDialog(context);
			mensaje.setMessage("Cargando datos...");
			// Bloqueamos toda la pantalla
			mensaje.setCancelable(false);
			// Mostramos el mensaje en pantalla
			mensaje.show();

		}
		

		@Override
		protected String doInBackground(String... params) {

			String resultado = "";

			for (String url : params){

				try {
					resultado = RESTClient
							.connectAndReturnResponse(url);
				} catch (Exception e) {

				}

			}

			return resultado;
		}

		@Override
		protected void onPostExecute(String json) {

			super.onPostExecute(json);
			// Sacamos el mensaje de la pantalla
			mensaje.dismiss();
			//Log.d("json", "json: " + json);
			
			//Mandamos los datos al método que mostrará 
			//la información
			muestraDatos(json);

		}
/*
	
		
		@Override
		protected ArrayList<UsuariosModel> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		protected void onPostExecute(ArrayList<UsuariosModel> result) {
			
			super.onPostExecute(result);
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {

			super.onProgressUpdate(values);
		}


*/


	}

}
