package com.area51.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.area51.clase02a.R;
import com.area51.models.PersonaModel;

public class PersonaAdapter extends BaseAdapter {

	ArrayList<PersonaModel> lista;
	Context context;

	public PersonaAdapter(ArrayList<PersonaModel> lista, Context context) {
		super();

		this.lista = lista;
		this.context = context;
	}

	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	// Clase estatica para optimizar
	// la relación en memoria de los elementos gráficos
	static class ViewHolder {
		public TextView nombre;
	}

	@Override
	public View getView(int position, View vistaActual, 
			ViewGroup parent) {

		ViewHolder v;

		if (vistaActual == null) {

			// Inicializamos el ViewHolder
			v = new ViewHolder();

			// Cargamos el xml
			vistaActual = LayoutInflater
					.from(context)
					.inflate(R.layout.item_persona, null, false);

			// Relacionamos el Textview hacia el ViewHolder
			v.nombre = (TextView) vistaActual.findViewById(R.id.nombre);
			// Guardamos la referencia de la clase
			// estatica en el atributo tag() del View
			vistaActual.setTag(v);

		} else {
			// Traemos la relación guardada en el atributo
			// tag()
			v = (ViewHolder) vistaActual.getTag();

		}

		v.nombre.setText(lista.get(position).getNombrePersona());

		return vistaActual;
	}

	/*
	 * Así no se hace
	 * 
	 * @Override public View getView(int position, View view, ViewGroup
	 * container) {
	 * 
	 * TextView nombre = null;
	 * 
	 * if (view == null) {
	 * 
	 * view = LayoutInflater.from(context).inflate(R.layout.item_persona, null,
	 * false);
	 * 
	 * nombre = (TextView) view.findViewById(R.id.nombre);
	 * 
	 * }
	 * 
	 * // Asignamos el valor del elemento al textview Log.d("dev","item: " +
	 * lista.get(position).getNombrePersona() );
	 * 
	 * nombre.setText(lista.get(position).getNombrePersona());
	 * 
	 * return view; }
	 */
}
