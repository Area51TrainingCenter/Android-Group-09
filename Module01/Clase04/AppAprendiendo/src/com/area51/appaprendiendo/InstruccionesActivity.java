package com.area51.appaprendiendo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;

public class InstruccionesActivity extends Activity {

	int valor = 0;

	private class ImagePagerAdapter extends PagerAdapter {
		private final int[] mImages = new int[] { R.drawable.usoa,
				R.drawable.usob, R.drawable.usoc, R.drawable.usod };

		@Override
		public void destroyItem(final ViewGroup container, final int position,
				final Object object) {

			if (valor == 0) {

				if (position == 1) {
					valor = 1;
					btnentiendo.setVisibility(View.VISIBLE);
				}

			}

			((ViewPager) container).removeView((ImageView) object);
		}

		@Override
		public int getCount() {
			return this.mImages.length;
		}

		@Override
		public Object instantiateItem(final ViewGroup container,
				final int position) {
			final Context context = InstruccionesActivity.this;
			final ImageView imageView = new ImageView(context);
			final int padding = context.getResources().getDimensionPixelSize(
					R.dimen.padding_medium);
			imageView.setPadding(padding, padding, padding, padding);
			imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			imageView.setImageResource(this.mImages[position]);
			((ViewPager) container).addView(imageView, 0);

			return imageView;
		}

		@Override
		public boolean isViewFromObject(final View view, final Object object) {
			return view == ((ImageView) object);
		}
	}

	Button btnentiendo;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instrucciones);

		btnentiendo = (Button) findViewById(R.id.btnentiendo);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		final ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);

		final CirclePageIndicator circleIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		circleIndicator.setViewPager(viewPager);

	}

	public void Inicia(View view) {

		finish();

	}

}