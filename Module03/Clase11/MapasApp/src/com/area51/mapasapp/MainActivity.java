package com.area51.mapasapp;

import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MainActivity extends Activity
implements LocationListener, ConnectionCallbacks,
OnConnectionFailedListener
{

	GoogleMap map;
	LocationClient locationClient;
	Context context;
	Locale myLocale;
	
	String TAG = "MapasApp";
	
	LocationRequest REQUEST = LocationRequest
			.create()
			.setInterval(2000)
			.setPriority( LocationRequest.PRIORITY_HIGH_ACCURACY );
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		
		context = this;
		loadMap();
		loadClient();
		locationClient.connect();
				
	}
	
	public void loadMap(){
		
		Log.d(TAG, "loadMap()");
		if( map == null ){
			map = ( (MapFragment)getFragmentManager()
					.findFragmentById(R.id.map) )
					.getMap();
			map.setBuildingsEnabled(true);
			//map.setMyLocationEnabled(true);
		}
		
	}

	public void loadClient(){
		
		locationClient = new LocationClient(
				getApplicationContext(), 
				this,
				this);
	}
	
	public void MyLocation( Location location ){
		//MOSTRAMOS NUESTRA UBICACIÓN
		
		//location.getLatitude();
		//location.getLongitude();
		
		//RECIBIMOS LA LONGITUD Y LATITUD
		LatLng ltnlng 
		= new LatLng(
				location.getLatitude(), 
				location.getLongitude());
		
		//AGREGAMOS UN MARKER
		MarkerOptions mo = new MarkerOptions();
		//Le ponemos un titulo
		mo.title(  getResources().getString(R.string.marker_titulo) );
		//Le ponemos una descripción
		mo.snippet( 
				getResources()
					.getString(R.string.marker_descripcion) 
				+ location.getLatitude() 
				+ " - " 
				+ location.getLongitude()
				);
		//Le asignamos una imagen como icono
		mo.icon( BitmapDescriptorFactory
				.fromResource(R.drawable.ic_launcher)  );
		//Le damos la ubicación
		mo.position(ltnlng);
		
		//Añadimos el marker al mapa
		map.addMarker(mo);
		
		//Habilitamos la camara
		CameraPosition cp = new CameraPosition(
				ltnlng, 
				13, 
				45, 
				30);
		
		map.animateCamera( 
				CameraUpdateFactory.newCameraPosition(cp)  
				);
		
		
		
		
	}
	

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		locationClient
		.requestLocationUpdates(REQUEST, this);
		
		//Llamamos al API
		new RutasApi().execute(Utilitarios.API);
		
	}

	@Override
	public void onDisconnected() {
		
	}

	@Override
	public void onLocationChanged(Location location) {
		//OBTENEMOS LA ÚLTIMA UBICACIÓN
		//MyLocation( locationClient.getLastLocation()  );
		
		
	}
	
	
	/******************************\
	PARA EL MENU
	\******************************/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		
		switch (id) {
		case R.id.mapa_normal:
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case R.id.mapa_hibrido:
			map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			break;
		case R.id.mapa_terreno:
			map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			break;
		case R.id.mapa_satelital:
			map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.idioma_espaniol:
			cambiaIdioma("es");
			break;
		case R.id.idioma_frances:
			cambiaIdioma("fr");
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	
	

	/******************************\
	PARA EL IDIOMA
	\******************************/
	public void cambiaIdioma(String language){
		
		myLocale = new Locale(language);
		//Configuraciones de idioma
		Resources resources = getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		Configuration conf = resources.getConfiguration();
		conf.locale = myLocale;
		resources.updateConfiguration(conf, dm);
		
		//Cargamos nuevamente la actividad
		Intent refresh = new Intent(this, MainActivity.class);
		startActivity(refresh);
		
	}
	
	

	/******************************\
	PARA EL CONSUMO DEL API
	\******************************/
	
	class RutasApi extends AsyncTask<String, Void, String>{

		private ProgressDialog pd;		
		
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			
			pd = new ProgressDialog(context);
			pd.setTitle( getResources().getString(R.string.ruta_mensaje) );
			pd.setCancelable(false);
			pd.show();
			
		}
		
		
		@Override
		protected String doInBackground(String... params) {
		
			String response = "";		
			
			for( String url : params ){
				
				response = RESTClient.connectAndReturnResponse(url);
			}				
			
			return response;
		}
		
		@Override
		protected void onPostExecute(String json) {
		
			super.onPostExecute(json);
			//Quitamos el mensaje
			pd.dismiss();
			//MÉTODO PARA PARSEAR EL JSON
			cargaRutas(json);
		}
		
	}
	

	/******************************\
	PARA EL PARSEAR EL JSON
	\******************************/
	public void cargaRutas(String json){
		
		try {
			
			JSONObject data = new JSONObject(json);
			
			if( data.getString("response").equals("OK") ){
				
				JSONArray rutas = data.getJSONArray("data");
				int total = data.getInt("total");				
				
				//Nos ubicamos en el mapa
				//de acuerdo a nuestra localización
				JSONObject inicio = (JSONObject)rutas.get(total - 1);
				LatLng lInicio = new LatLng(
						inicio.getDouble("LATITUDE"),
						inicio.getDouble("LONGITUDE"));
				
				
				
				
				
				
				//Seteamos la ubicación
				CameraPosition cp = new CameraPosition
						(lInicio, 13, 45, 30);
				//Iniciamos la animación hacia ese lugar
				map.animateCamera(
						CameraUpdateFactory.newCameraPosition(cp)
						);
				
				
				//Habilitamos las líneas
				PolygonOptions poO = new PolygonOptions();
				poO.strokeColor(Color.RED);				
				
				for (int i = 0; i < total; i++) {
					//Recorremos los elementos
					JSONObject elemento = (JSONObject)rutas.get(i);
					LatLng ll = new LatLng(
							elemento.getDouble("LATITUDE"),
							elemento.getDouble("LONGITUDE"));					
					poO.add(ll);
					
				}
				//Agregamos al mapa el poligono
				map.addPolygon(poO);
				
				
			}else{
				
				Toast.makeText(context, 
						"No hay datos", Toast.LENGTH_SHORT)
						.show();
				
			}
			
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
}
