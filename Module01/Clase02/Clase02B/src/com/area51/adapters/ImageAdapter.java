package com.area51.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.area51.clase02b.R;
import com.area51.models.ImageModel;

public class ImageAdapter extends ArrayAdapter<ImageModel> {

	Context context;

	public ImageAdapter(Context context, 
			ArrayList<ImageModel> lista) {
		
		super(context, 0, lista);
		this.context = context;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		ViewHolder vh;

		if (view == null) {

			view = LayoutInflater.from(context).inflate(R.layout.item_gridview,
					null, false);

			vh = new ViewHolder();

			vh.imagen = (ImageView) view.findViewById(R.id.imagen);

			view.setTag(vh);

		} else {

			vh = (ViewHolder) view.getTag();

		}

		Log.d("", "position: " + position);

		// Asignamos los valores
		// Asignamos una imagen que esta dentro del app
		vh.imagen.setImageResource(getItem(position).getRutaInternaImagen());

		return view;
	}

	static class ViewHolder {

		public ImageView imagen;

	}

}
