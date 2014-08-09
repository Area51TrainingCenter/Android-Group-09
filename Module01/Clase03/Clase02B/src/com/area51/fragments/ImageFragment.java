package com.area51.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.area51.clase02b.R;
import com.area51.libraries.ImageLoader;
import com.area51.utils.Constantes;

public class ImageFragment extends Fragment {

	ImageView detalle_imagen;
	TextView detalle_nombre;

	public String parametro = "parametro";
	int recibido;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
			
		
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.item_detail, null, false);

		detalle_imagen = (ImageView) view.findViewById(R.id.detalle_imagen);
		detalle_nombre = (TextView) view.findViewById(R.id.detalle_nombre);

		return view;
	}
	
	@Override
	public void onResume() {
	
		super.onResume();
		
		//Recibimos la posicion del elemento seleccionado
		recibido = getArguments().getInt(parametro);
		
		Log.d("app","" + recibido);
		
		//Obtenemos la imagen a mostrar
		int imagen = Constantes.lista.get(recibido)
				.getRutaInternaImagen();
		
		//Mostramos la imagen escogida
		//detalle_imagen.setImageResource( imagen);
		
		
		// Asignamos una imagen de internet
		ImageLoader imageLoader = new ImageLoader( getActivity() );
		imageLoader.DisplayImage( 
				 Constantes.lista.get(recibido).getRutaExternaImagen(),
				detalle_imagen);
	
		
	}
	
	

}
