package com.harlan.libs.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

public class ToastUtils {
	public static void Short(Context context,@NonNull Object obj){
		Toast.makeText(context, obj.toString(), Toast.LENGTH_SHORT).show();
	}
	public static void Long(Context context,@NonNull Object obj){
		Toast.makeText(context, obj.toString(), Toast.LENGTH_LONG).show();
	}
	public static void test(Context context){
		Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
	}
}
