package com.area51.adapters;

import com.area51.models.TweetModel;
import com.area51.twitterapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetAdapter extends ArrayAdapter<TweetModel> {

	Context context;

	public TweetAdapter(Context context, int resource) {
		super(context, 0);

		this.context = context;
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

		vh.username.setText(getItem(position).getUsername());
		vh.email.setText(getItem(position).getEmail());
		vh.detail.setText(getItem(position).getDetail());		

		return view;
	}

	static class ViewHolder {

		public ImageView image;
		public TextView username;
		public TextView email;
		public TextView detail;

	}

}
