package com.area51.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.area51.appws.R;
import com.area51.models.UsuariosModel;

public class UsuariosAdapter extends ArrayAdapter<UsuariosModel> {

	// Variables de clase
	Context context;
	ArrayList<UsuariosModel> arreglo;

	// Constructor
	public UsuariosAdapter(Context context, ArrayList<UsuariosModel> arreglo) {
		super(context, 0, arreglo);
		// Referenciamos a las variables de clase

		this.context = context;
		this.arreglo = arreglo;

	}

	static class ViewHolder {

		public ImageView imagen;
		public TextView correo;
		public TextView nombre;
		public TextView registro;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		ViewHolder vh;
		
		if( view == null ){
			
			view = LayoutInflater.from(context)
					.inflate(R.layout.item_usuario, null, false);
			
			vh = new ViewHolder();
			
			vh.imagen = (ImageView)view.findViewById(R.id.imagen);
			vh.correo = (TextView)view.findViewById(R.id.correo);
			vh.nombre = (TextView)view.findViewById(R.id.nombre);
			vh.registro = (TextView)view.findViewById(R.id.registro);
			
			view.setTag(vh);
			
		}else{
			
			vh = (ViewHolder)view.getTag();
			
		}

		vh.correo.setText(arreglo.get(position).getCorreo());
		//vh.nombre.setText( getItem(position).getNombre() );
		vh.nombre.setText(arreglo.get(position).getNombre() );
		vh.registro.setText(arreglo.get(position).getRegistro());
			
		return view;
	}

}





