package com.harlan.libs.utils;

import android.util.TypedValue;

import com.harlan.libs.core.Harlan;

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
				dpVal, Harlan.getContext().getResources().getDisplayMetrics());
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
				spVal, Harlan.getContext().getResources().getDisplayMetrics());
	}

	/**
	 * px转dp
	 * 
	 * @param context
	 * @param pxVal
	 * @return
	 */
	public static float px2dp(float pxVal) {
		final float scale = Harlan.getContext().getResources()
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
		return (pxVal / Harlan.getContext().getResources().getDisplayMetrics().scaledDensity);
	}

}