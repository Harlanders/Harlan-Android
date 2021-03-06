package com.harlan.libs.core;

import android.content.Context;

/**
 * @author Harlan
 * 
 * @note 这里应该使用构造者模式
 */
public class ApplicationManager {

	/**
	 * Application Context
	 */
	private static Context context;
	private static String appName = "Harlan";

	public static Context getContext() {
		if (context == null) {
			throw new NullPointerException(
					"initialize Harlan context in application");
		}
		return context;
	}

	public static String getAppName() {
		return appName;
	}

	public static void init(Context context) {
		ApplicationManager.context = context;
	}

	public static void setAppName(String appName) {
		ApplicationManager.appName = appName;
	}
}