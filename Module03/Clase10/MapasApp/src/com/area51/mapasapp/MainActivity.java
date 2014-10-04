package com.area51.mapasapp;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

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
	}

	@Override
	public void onDisconnected() {
		
	}

	@Override
	public void onLocationChanged(Location location) {
		//OBTENEMOS LA ÚLTIMA UBICACIÓN
		MyLocation( locationClient.getLastLocation()  );
		
		
	}
	

}
