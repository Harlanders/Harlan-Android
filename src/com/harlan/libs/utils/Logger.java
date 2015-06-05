package com.harlan.libs.utils;

import android.util.Log;

import com.harlan.libs.BuildConfig;

/**
 * Log Manager,自动获取Tag
 * 
 * @author Harlan
 * @data 2015年5月30日 18:42:45
 */
public final class Logger {

	private static boolean DEBUG = BuildConfig.DEBUG;

	private Logger() {
	}

	private static String getTag() {
		StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4];

		String className = stackTrace.getClassName();
		className = className.substring(className.lastIndexOf(".") + 1);

		String format = "%s.%s(L:%d)";
		String tag = String.format(format, className,
				stackTrace.getMethodName(), stackTrace.getLineNumber());

		return tag;
	}

	public static void i(Object msg) {
		if (DEBUG) {
			Log.i(getTag(), msg.toString());
		}
	}

	public static void i(String msgName, Object msg) {
		i(msgName + " = " + msg.toString());
	}

	public static void d(Object msg) {
		if (DEBUG) {
			Log.d(getTag(), msg.toString());
		}
	}

	public static void d(String msgName, Object msg) {
		d(msgName + " = " + msg.toString());
	}

	public static void w(Object msg) {
		if (DEBUG) {
			Log.w(getTag(), msg.toString());
		}
	}

	public static void w(String msgName, Object msg) {
		w(msgName + " = " + msg.toString());
	}

	public static void e(Object msg) {
		if (DEBUG) {
			Log.e(getTag(), msg.toString());
		}
	}

	public static void e(String msgName, Object msg) {
		e(msgName + " = " + msg.toString());
	}

	public static void v(Object msg) {
		if (DEBUG) {
			Log.v(getTag(), msg.toString());
		}
	}

	public static void v(String msgName, Object msg) {
		v(msgName + " = " + msg.toString());
	}
}
