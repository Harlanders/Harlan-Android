package com.harlan.libs.core;

import android.content.Context;

public class Harlan {

	/**
	 * Application Context
	 */
	private static Context context;
	private static String appName = "Harlan";

	public static Context getContext() {
		if (context == null)
			throw new NullPointerException(
					"initialize Harlan context in application");
		return context;
	}

	public static String getAppName() {
		return appName;
	}

	public static void init(Context context) {
		Harlan.context = context;
	}

	public static void setAppName(String appName) {
		Harlan.appName = appName;
	}
}