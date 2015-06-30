package com.harlan.libs.ui.view.ViewPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;

public class EndlessWrapContentHeightViewPager extends
		WrapContentHeightViewPager {

	public EndlessWrapContentHeightViewPager(Context context) {
		this(context, null);
	}

	public EndlessWrapContentHeightViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setAdapter(PagerAdapter arg0) {
		super.setAdapter(arg0);
		setCurrentItem(499);
	}

}
