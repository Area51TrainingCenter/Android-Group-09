package com.area51.fragments;

import java.util.ArrayList;

import com.area51.adapters.TweetAdapter;
import com.area51.async.ManageTweet;
import com.area51.models.TweetModel;
import com.area51.twitterapp.R;
import com.area51.utils.ConstantsApp;
import com.area51.utils.NetworkApp;

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

public class InicioFragment extends Fragment {

	ListView listainicio;
	LinearLayout capacargando;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_inicio, null, false);

		listainicio = (ListView) view.findViewById(R.id.listaInicio);
		capacargando = (LinearLayout) view.findViewById(R.id.capacargando);

		return view;
	}

	@Override
	public void onResume() {

		super.onResume();

		// Verificar la conexión de red
		NetworkApp conexion = new NetworkApp(getActivity());

		if (conexion.getNetwork()) {
			// Llamamos al rest api o web services
			new InicioAsync().execute();

		} else {
			// Llamamos a sqlite
		}

	}

	public void mostrarTweets( ArrayList<TweetModel> arreglo ){
		
	}
	
	
	class InicioAsync extends AsyncTask<Object, Void, ArrayList<TweetModel>> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			Log.d("InicioAsync", "onPreExecute");
		}

		@Override
		protected ArrayList<TweetModel> doInBackground(Object... params) {

			Log.d("InicioAsync", "doInBackground");
			return ManageTweet.getHashtag(ConstantsApp.TWITTER_TERM);
		}

		@Override
		protected void onPostExecute(ArrayList<TweetModel> result) {
			
			super.onPostExecute(result);
			Log.d("InicioAsync", "doInBackground");
			
			mostrarTweets(result);
			
			
		}

	}

}
