package com.area51.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.area51.adapters.TweetAdapter;
import com.area51.async.ManageTweet;
import com.area51.sqlite.DBOperations;
import com.area51.twitterapp.R;
import com.area51.utils.ConstantsApp;
import com.area51.utils.NetworkApp;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

public class InicioFragment extends Fragment 
implements OnRefreshListener 
{

	ListView listainicio;
	LinearLayout capacargando;
	
	ManageTweet mt;
	
	//PARA LA ANIMACION
	SwingBottomInAnimationAdapter sbiaa;
	
	//PARA EL REFRESH
	SwipeRefreshLayout swiperefresh;
	NetworkApp conexion;
	
	//PARA BASE DE DATOS
	DBOperations dbo;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_inicio, null, false);

		listainicio = (ListView) view.findViewById(R.id.listaInicio);
		capacargando = (LinearLayout) view.findViewById(R.id.capacargando);

		swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
		
		return view;
	}

	@Override
	public void onResume() {

		super.onResume();

		mt = new ManageTweet(getActivity());
		
		swiperefresh.setOnRefreshListener(this);
		swiperefresh.setColorSchemeResources(
				android.R.color.holo_blue_dark,
				android.R.color.holo_orange_light,
				android.R.color.holo_green_dark,
				android.R.color.holo_red_light);
		
				
		// Verificar la conexión de red
		conexion = new NetworkApp(getActivity());
		dbo = new DBOperations(getActivity());
		
		onRefresh();
		

	}

	public void mostrarTweets( TweetAdapter adapter ){
		
		//Ocultamos el layout con el loader
		capacargando.setVisibility(View.GONE);
		
		//Verificamos que haya información
		
		if( !adapter.isEmpty() ){
			
			listainicio.setVisibility(View.VISIBLE);
			
			sbiaa = new SwingBottomInAnimationAdapter(
					new SwingBottomInAnimationAdapter(adapter)
			);
			
			sbiaa.setAnimationDelayMillis(300);
			sbiaa.setAbsListView(listainicio);
			
			listainicio.setAdapter(sbiaa);		
			
			//CON ESTO DETENEMOS LA ANIMACIÓN DE COLORES
			swiperefresh.setRefreshing(false);
						
			
		}else{
			//Mostramos un mensaje
			Toast.makeText(
					getActivity(), 
					R.string.texto_dato_vacio , 
					Toast.LENGTH_SHORT).show();
			
		}
		
		
	}
	
	
	class InicioAsync extends AsyncTask<Object, Void, TweetAdapter> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}	

		@Override
		protected TweetAdapter doInBackground(Object... params) {
			
			return mt.getHashtagAdapter(ConstantsApp.TWITTER_TERM);
		}

		@Override
		protected void onPostExecute(TweetAdapter result) {
			
			super.onPostExecute(result);
			Log.d("InicioAsync", "doInBackground");
			
			mostrarTweets(result);			
		}

	}


	@Override
	public void onRefresh() {

		if (conexion.getNetwork()) {
			// Llamamos al rest api o web services
			new InicioAsync().execute();

		} else {
			// Llamamos a sqlite
			
			mostrarTweets( dbo.getStatusUpdates() );
		}
		
	}

}
