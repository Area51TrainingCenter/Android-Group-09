package com.area51.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.area51.adapters.TweetAdapter;
import com.area51.async.ManageTweet;
import com.area51.twitterapp.R;
import com.area51.utils.ConstantsApp;
import com.area51.utils.NetworkApp;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

public class ActividadFragment extends Fragment {

	ListView listaActividad;
	LinearLayout capacargando;
	
	ManageTweet mt;
	SwingBottomInAnimationAdapter sbiaa;
	

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_actividad, null, false);

		listaActividad = (ListView) view.findViewById(R.id.listaActividad);
		capacargando = (LinearLayout) view.findViewById(R.id.capacargando);

		return view;
	}

	@Override
	public void onResume() {

		super.onResume();
		
		mt = new ManageTweet(getActivity());

		// Verificar la conexión de red
		NetworkApp conexion = new NetworkApp(getActivity());

		if (conexion.getNetwork()) {
			// Llamamos al rest api o web services
			new ActividadAsync().execute();

		} else {
			// Llamamos a sqlite
		}

	}

	public void mostrarTweets( TweetAdapter adapter ){
		
		//Ocultamos el layout con el loader
		capacargando.setVisibility(View.GONE);
		
		//Verificamos que haya información
		
		if( !adapter.isEmpty() ){
			//Llenamos el adapter
			listaActividad.setVisibility(View.VISIBLE);
			
			sbiaa = new SwingBottomInAnimationAdapter(
					new SwingBottomInAnimationAdapter(adapter)
					);
			
			sbiaa.setAnimationDelayMillis(300);
			sbiaa.setAbsListView(listaActividad);
			
			listaActividad.setAdapter(sbiaa);			
			
		}else{
			//Mostramos un mensaje
			Toast.makeText(
					getActivity(), 
					R.string.texto_dato_vacio , 
					Toast.LENGTH_SHORT).show();
			
			
		}
		
		
	}
	
	
	class ActividadAsync extends AsyncTask<Object, Void, TweetAdapter> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			Log.d("InicioAsync", "onPreExecute");
		}

		@Override
		protected TweetAdapter doInBackground(Object... params) {

			Log.d("InicioAsync", "doInBackground");
			return mt.getTimelineAdapter(ConstantsApp.TWITTER_USER);
		}

		@Override
		protected void onPostExecute(TweetAdapter result) {
			
			super.onPostExecute(result);
			Log.d("InicioAsync", "doInBackground");
			
			mostrarTweets(result);
			
			
		}

	}
}
