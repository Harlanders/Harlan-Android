package com.harlan.libs.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public abstract class CustomView extends View {

	public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	public CustomView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomView(Context context) {
		this(context, null);
	}

	protected abstract void init(Context context, AttributeSet attrs);
}
