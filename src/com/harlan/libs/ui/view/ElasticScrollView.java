package com.harlan.libs.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.harlan.libs.ui.listener.SimpleOnGestureListener;
import com.harlan.libs.utils.Logger;

public class ElasticScrollView extends LinearLayout {

	private Scroller scroller;
	private GestureDetector gestureDetector;
	private SimpleOnGestureListener simpleOnGestureListener = new SimpleOnGestureListener() {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			int dis = (int) ((distanceY - 0.5) / 2);
			smoothlyScrollBy(0, dis);
			return false;
		};
	};

	private void init(Context context) {
		// setClickable(true);
		// setLongClickable(true);
		scroller = new Scroller(context);
		gestureDetector = new GestureDetector(context, simpleOnGestureListener);
	}

	public ElasticScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ElasticScrollView(Context context) {
		this(context, null);

	}

	public void smoothlyScrollTo(int x, int y) {
		Logger.i("smoothlyScrollTo");
		int offsetX = x - scroller.getFinalX();
		int offsetY = y - scroller.getFinalY();
		smoothlyScrollBy(offsetX, offsetY);
	}

	public void smoothlyScrollBy(int x, int y) {
		Logger.i("smoothlyScrollBy");
		scroller.startScroll(scroller.getFinalX(), scroller.getFinalY(), x, y,
				1500);
		invalidate();
	}

	@Override
	public void computeScroll() {
		Logger.i("computeScroll");
		if (scroller.computeScrollOffset()) {
			scrollTo(scroller.getCurrX(), scroller.getCurrY());
			invalidate();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Logger.i("onTouchEvent");
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			smoothlyScrollTo(0, 0);
			break;
		default:
			return gestureDetector.onTouchEvent(event);
		}
		return super.onTouchEvent(event);
	}

}
