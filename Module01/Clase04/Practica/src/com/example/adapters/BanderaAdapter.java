package com.example.adapters;

import java.util.ArrayList;

import com.example.models.BanderaModel;
import com.example.practica.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class BanderaAdapter extends ArrayAdapter<BanderaModel> {

	Context context;

	public BanderaAdapter(Context context, ArrayList<BanderaModel> arreglo) {
		super(context, 0, arreglo);
		this.context = context;
	}

	static class ViewHolder {
		public ImageView imgBandera;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		ViewHolder vh;

		if (view == null) {

			view = LayoutInflater.from(context).inflate(R.layout.item_bandera,
					null, false);

			vh = new ViewHolder();

			vh.imgBandera = (ImageView) view.findViewById(R.id.imgBandera);

			view.setTag(vh);

		} else {

			vh = (ViewHolder) view.getTag();

		}

		vh.imgBandera.setImageResource(getItem(position).getImgBandera());

		Log.d("practica","size: " + position);
		
		
		return view;
	}

}
