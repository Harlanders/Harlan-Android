package com.harlan.libs.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 走马灯
 */
public class MarqueeTextView extends TextView {

	public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public MarqueeTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MarqueeTextView(Context context) {
		this(context, null);
	}

	private void init() {
		setSingleLine();
		setEllipsize(TruncateAt.MARQUEE);
		setMarqueeRepeatLimit(-1);
		setFocusableInTouchMode(true);
		setFocusable(true);
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		if (focused) {
			super.onFocusChanged(focused, direction, previouslyFocusedRect);
		}
	}

	@Override
	public void onWindowFocusChanged(boolean focused) {
		if (focused) {
			super.onWindowFocusChanged(focused);
		}
	}

	@Override
	public boolean isFocused() {
		return true;
	}
}
