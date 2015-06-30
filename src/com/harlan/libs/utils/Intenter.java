package com.harlan.libs.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Easily use Intent
 * 
 * @author Harlan
 *
 * @note 使用非Activity的context时需要NEW_TASK，不建议使用
 */
public class Intenter {
	public static void startActivityForResult(Activity activity, Class<?> clz,
			int requestCode) {
		Intent intent = new Intent(activity, clz);
		activity.startActivityForResult(intent, requestCode);
	}

	public static void startActivity(Activity activity, Class<?> clz) {
		Intent intent = new Intent(activity, clz);
		activity.startActivity(intent);
	}
}
