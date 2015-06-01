package com.harlan.libs.utils;

import android.content.Context;
import android.widget.Toast;

import com.harlan.libs.core.Harlan;

/**
 * Toast Manager
 * 
 * @author Harlan
 * @date 2015年5月31日 13:29:36
 */
public class Toastor {
	private static Toast toast;
	private static Context context = Harlan.getContext();

	private Toastor() {
	}

	/**
	 * @param context
	 * @param obj
	 * @param duration
	 */
	private static void show(Context context, Object obj, int duration) {
		if (toast == null)
			toast = Toast.makeText(context, obj.toString(), duration);
		else {
			toast.setDuration(duration);
			toast.setText(obj.toString());
		}
		toast.show();
	}

	public static void showShort(Object obj) {
		showShort(context, obj);
	}

	public static void showLong(Object obj) {
		showLong(context, obj);
	}

	public static void showTest() {
		showTest(context);
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
}
