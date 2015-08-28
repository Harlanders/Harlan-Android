package com.harlan.libs.ui.listener;

import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

/**
 * onDown return true
 * 
 * @author Administrator
 *
 */
public abstract class SimpleOnGestureListener implements OnGestureListener {

	@Override
	public boolean onDown(MotionEvent e) {

		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {

		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {

		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		return false;
	}

}
