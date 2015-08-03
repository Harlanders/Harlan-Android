package com.harlan.libs.ui.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 设置无限滚动ViewPager
 */
public class EndlessViewPager extends ViewPager {

	public static final int CENTER_INDEX = 499;

	public EndlessViewPager(Context context) {
		this(context, null);
	}

	public EndlessViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setAdapter(PagerAdapter arg0) {
		super.setAdapter(arg0);
		setCurrentItem(CENTER_INDEX);
	}
}
