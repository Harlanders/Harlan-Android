package com.harlan.libs.ui.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;

public class ViewInject {
	public static final String SETCONTENTVIEW = "setContentView";
	public static final String FINDVIEWBYID = "findViewById";

	public static void init(Activity activity) {
		injectContentView(activity);
		injectFindViewById(activity);
	}

	/**
	 * Inject views
	 * 
	 * @param activity
	 */
	private static void injectFindViewById(Activity activity) {
		Class<? extends Activity> clz = activity.getClass();
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			FindViewById findViewById = field.getAnnotation(FindViewById.class);
			if (findViewById != null) {
				// Logger.i("findViewById != null");
				int id = findViewById.id();
				if (id > 0) {
					try {
						Method mFindViewById = clz.getMethod(FINDVIEWBYID,
								int.class);
						Object view = mFindViewById.invoke(activity, id);
						field.setAccessible(true);
						field.set(activity, view);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				// Logger.i("findViewById == null");
			}
		}
	}

	/**
	 * Inject ContentView
	 * 
	 * @param activity
	 */
	private static void injectContentView(Activity activity) {
		Class<? extends Activity> clz = activity.getClass();
		SetContentView setContentView = clz.getAnnotation(SetContentView.class);
		if (setContentView != null) {
			// Logger.i("setContentView != null");
			int layoutId = setContentView.layoutId();
			try {
				Method mSetContentView = clz.getMethod(SETCONTENTVIEW,
						int.class);
				mSetContentView.setAccessible(true);
				mSetContentView.invoke(activity, layoutId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// Logger.i("setContentView == null");
		}
	}
}
