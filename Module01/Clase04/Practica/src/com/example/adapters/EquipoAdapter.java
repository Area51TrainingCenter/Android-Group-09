package com.example.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.models.EquipoModel;
import com.example.practica.R;

public class EquipoAdapter extends ArrayAdapter<EquipoModel> {

	Context context;

	public EquipoAdapter(Context context, ArrayList<EquipoModel> arreglo) {
		super(context, 0, arreglo);

		this.context = context;

	}

	static class ViewHolder {
		public ImageView imgEquipo;
		public TextView tituloEquipo;
		public TextView detalleEquipo;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		ViewHolder vh;

		if (view == null) {

			view = LayoutInflater.from(context).inflate(R.layout.item_equipo,
					null, false);

			vh = new ViewHolder();
			// Relación con la parte grafica
			vh.imgEquipo = (ImageView) view.findViewById(R.id.imgEquipo);
			vh.tituloEquipo = (TextView) view.findViewById(R.id.tituloEquipo);
			vh.detalleEquipo = (TextView) view.findViewById(R.id.detalleEquipo);

			// Guardamos la referencia
			view.setTag(vh);

		} else {
			vh = (ViewHolder) view.getTag();
		}

		// Mandamos los valores
		vh.imgEquipo.setImageResource(getItem(position).getImgEquipo());
		vh.tituloEquipo.setText(getItem(position).getNombreEquipo());
		vh.detalleEquipo.setText(getItem(position).getDetalleEquipo());

		return view;
	}

}
