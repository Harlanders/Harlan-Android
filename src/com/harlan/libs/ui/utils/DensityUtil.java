package com.harlan.libs.ui.utils;

import android.util.TypedValue;

import com.harlan.libs.core.ApplicationManager;

public class DensityUtil {
	private DensityUtil() {
	}

	/**
	 * dp转px
	 * 
	 * @param context
	 * @param val
	 * @return
	 */
	public static int dp2px(float dpVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, ApplicationManager.getContext().getResources().getDisplayMetrics());
	}

	/**
	 * sp转px
	 * 
	 * @param context
	 * @param val
	 * @return
	 */
	public static int sp2px(float spVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				spVal, ApplicationManager.getContext().getResources().getDisplayMetrics());
	}

	/**
	 * px转dp
	 * 
	 * @param context
	 * @param pxVal
	 * @return
	 */
	public static float px2dp(float pxVal) {
		final float scale = ApplicationManager.getContext().getResources()
				.getDisplayMetrics().density;
		return (pxVal / scale);
	}

	/**
	 * px转sp
	 * 
	 * @param fontScale
	 * @param pxVal
	 * @return
	 */
	public static float px2sp(float pxVal) {
		return (pxVal / ApplicationManager.getContext().getResources().getDisplayMetrics().scaledDensity);
	}

}