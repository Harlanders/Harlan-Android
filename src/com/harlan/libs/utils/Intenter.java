package com.harlan.libs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class Intenter {
	public static void startActivityForResult(Activity activity, Class<?> clz,
			int requestCode) {
		Intent intent = new Intent(activity, clz);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivityForResult(intent, requestCode);
	}

	public static void startActivity(Context context, Class<?> clz) {
		Intent intent = new Intent(context, clz);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
}
