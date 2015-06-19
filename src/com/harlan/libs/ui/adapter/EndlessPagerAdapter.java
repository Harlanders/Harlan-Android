package com.harlan.libs.ui.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class EndlessPagerAdapter<V extends View> extends PagerAdapter {

	private List<V> views;

	public EndlessPagerAdapter(List<V> views) {
		this.views = views;
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View v, Object obj) {
		return v == obj;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		position = position % views.size();
		if (views.get(position).getParent() != null) {
			((ViewPager) views.get(position).getParent()).removeView(views
					.get(position));
		}
		((ViewPager) container).addView(views.get(position), 0);
		return views.get(position);
	}

	public List<V> getViews() {
		return views;
	}

}
