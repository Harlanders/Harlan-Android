package com.harlan.libs.ui.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;

public class QuickPagerAdapter<T> extends PagerAdapter {

	private List<T> data;

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public boolean isViewFromObject(View v, Object obj) {
		return v == obj;
	}

}
