package com.area51.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.area51.models.TweetModel;
import com.area51.twitterapp.R;
import com.area51.utils.BitmapManager;

public class TweetAdapter extends ArrayAdapter<TweetModel> {

	Context context;
	ArrayList<TweetModel> arreglo;

	public TweetAdapter(
			
			Context context, 
			int resource,
			ArrayList<TweetModel> arreglo
			
			){
		super(context, 0, arreglo);

		this.context = context;
		this.arreglo = arreglo;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		ViewHolder vh;

		if (view == null) {

			view = LayoutInflater.from(context).inflate(R.layout.tweet_xml,
					null, false);

			vh = new ViewHolder();

			vh.image = (ImageView) view.findViewById(R.id.image);
			vh.username = (TextView) view.findViewById(R.id.username);
			vh.email = (TextView) view.findViewById(R.id.email);
			vh.detail = (TextView) view.findViewById(R.id.detail);

			view.setTag(vh);

		} else {

			vh = (ViewHolder) view.getTag();

		}

		// Mostrar Imagen pendiente
		
		vh.username.setText(arreglo.get(position).getUsername());
		vh.email.setText(arreglo.get(position).getEmail());
		vh.detail.setText(arreglo.get(position).getDetail());
		
		String urlimage = arreglo.get(position).getPathImage();
		urlimage = urlimage.replace("normal", "200x200");
		
		BitmapManager
			.getInstance()
				.loadBitmap(
						urlimage, 
						vh.image);
		
		Log.d("BitmapManager","" + 
				arreglo.get(position).getPathImage());

		return view;
	}

	static class ViewHolder {

		public ImageView image;
		public TextView username;
		public TextView email;
		public TextView detail;

	}

}
