package com.harlan.libs.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.harlan.libs.core.Harlan;

public class SpUtil {
	private static SharedPreferences sp = Harlan.getContext()
			.getSharedPreferences(Harlan.getAppName(), Context.MODE_PRIVATE);
	private static SharedPreferences.Editor editor = sp.edit();

	public static void saveString(String key, String value) {
		editor.putString(key, value).commit();
	}

	public static String getString(String key, String defValue) {
		return sp.getString(key, defValue);
	}

	public static void saveLong(String key, long value) {
		editor.putLong(key, value).commit();
	}

	public static long getLong(String key, long defValue) {
		return sp.getLong(key, defValue);
	}
}
