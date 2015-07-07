package com.harlan.libs.ui.utils;

import java.lang.reflect.Method;

import android.app.Activity;

public class ViewJnject {
	public static final String SETCONTENTVIEW = "setContentView";

	public static void init(Activity activity) {
		Class<? extends Activity> clz = activity.getClass();
		SetContentView setContentView = clz.getAnnotation(SetContentView.class);
		if (setContentView != null) {
			int resId = setContentView.resId();
			try {
				Method mSetContentView = clz.getMethod(SETCONTENTVIEW,
						int.class);
				mSetContentView.setAccessible(true);
				mSetContentView.invoke(clz, resId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
