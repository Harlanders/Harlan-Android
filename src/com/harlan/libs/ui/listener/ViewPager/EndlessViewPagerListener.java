package com.harlan.libs.ui.listener.ViewPager;

import java.util.List;

import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.View;

import com.harlan.libs.ui.adapter.ViewPager.EndlessPagerAdapter;
import com.harlan.libs.ui.view.ViewPager.EndlessViewPager;

/**
 * 无限滚动ViewPager的页面变化监听器
 * 
 * @author Harlan
 *
 * @param <V>
 */
public abstract class EndlessViewPagerListener<V extends View> extends
		SimpleOnPageChangeListener {

	private int currentIndex = EndlessViewPager.CENTER_INDEX;
	private List<V> views;

	protected abstract void leftPageSelected(int position);

	protected abstract void rightPageSelected(int position);

	/**
	 * 设置页面变化监听器
	 */
	protected void setOnPageSelectedListener() {
	}

	public EndlessViewPagerListener(EndlessPagerAdapter<V> adapter) {
		views = adapter.getViews();
	}

	@Override
	public void onPageSelected(int position) {
		if (position > currentIndex) {
			rightPageSelected(position);
		} else if (position < currentIndex) {
			leftPageSelected(position);
		}
		setOnPageSelectedListener();
		currentIndex = position;
	}

	public V getCurrentView() {
		return getView(currentIndex);
	}

	public V getView(int position) {
		return views.get(position % views.size());
	}
}
