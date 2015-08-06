package com.harlan.libs.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.harlan.libs.ui.utils.ViewFinder;

public abstract class CombinationView extends LinearLayout {

	private ViewFinder finder;

	@SuppressLint("NewApi")
	public CombinationView(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		initData(attrs);
	}

	@SuppressLint("NewApi")
	public CombinationView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initData(attrs);
	}

	public CombinationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initData(attrs);
	}

	public CombinationView(Context context) {
		super(context);
	}

	private void initData(AttributeSet attrs) {
		if (getStyleable() != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs,
					getStyleable(), 0, 0);
			conver(a);
			a.recycle();
		}
	}

	/**
	 * 获取参数
	 */
	protected abstract void conver(TypedArray a);

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		View.inflate(getContext(), getLayoutId(), this);
		finder = new ViewFinder(this);
		initViews();
	}

	public ViewFinder getFinder() {
		return finder;
	}

	public <V extends View> V find(int id) {
		return getFinder().find(id);
	}

	/**
	 * Layout
	 */
	protected abstract int getLayoutId();

	/**
	 * attrs文件中自定义参数
	 * 
	 * @return
	 */
	protected abstract int[] getStyleable();

	/**
	 * 获取控件并设置
	 */
	protected abstract void initViews();

}
