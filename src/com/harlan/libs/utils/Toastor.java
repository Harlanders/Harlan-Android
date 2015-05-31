package com.harlan.libs.utils;

import com.harlan.libs.core.Harlan;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast Manager
 * 
 * @author Harlan
 * @date 2015年5月31日 13:29:36
 */
public class Toastor {
	private static Toast toast;
	private static Context context;

	private Toastor() {
	}

	/**
	 * @param context
	 * @param obj
	 *            string or resId
	 * @param duration
	 */
	private static void show(Context context, Object obj, int duration) {
		if (toast == null) {
			toast = Toast.makeText(context, obj.toString(), duration);
		} else {
			toast.setDuration(duration);
			toast.setText(obj.toString());

		}
		toast.show();
	}

	private static void showResString(Context context, int resId, int duration) {
		if (toast == null) {
			toast = Toast.makeText(context, resId, duration);
		} else {
			toast.setDuration(duration);
			toast.setText(resId);

		}
		toast.show();
	}

	private static void setContext() {
		if (context == null) {
			context = Harlan.getContext();
		}
	}

	public static void showShort(Object obj) {
		setContext();
		showShort(context, obj);
	}

	public static void showLong(Object obj) {
		setContext();
		showLong(context, obj);
	}

	public static void showTest() {
		setContext();
		showTest(context);
	}

	public static void showShortResString(int resId) {
		setContext();
		showShortResString(context, resId);
	}

	public static void showLongResString(int resId) {
		setContext();
		showLongResString(context, resId);
	}

	public static void showShort(Context context, Object obj) {
		show(context, obj, Toast.LENGTH_SHORT);
	}

	public static void showLong(Context context, Object obj) {
		show(context, obj, Toast.LENGTH_LONG);
	}

	public static void showTest(Context context) {
		show(context, "测试", Toast.LENGTH_SHORT);
	}

	public static void showShortResString(Context context, int resId) {
		showResString(context, resId, Toast.LENGTH_SHORT);
	}

	public static void showLongResString(Context context, int resId) {
		showResString(context, resId, Toast.LENGTH_LONG);
	}
}
