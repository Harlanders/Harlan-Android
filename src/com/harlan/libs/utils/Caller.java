package com.harlan.libs.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Easily use Phone&SMS
 * 
 * @author Harlan
 */
public class Caller {
	/**
	 * Show phone's panel
	 * 
	 * @param context
	 * @param phone
	 */
	public static void toPhoneUI(Context context, String phone) {
		Uri tel = Uri.parse("tel:" + phone);
		Intent intent = new Intent(Intent.ACTION_DIAL, tel);
		context.startActivity(intent);
	}

	/**
	 * Show SMS's panel
	 * 
	 * @param context
	 * @param phone
	 */
	public static void toSmsUI(Context context, String phone) {
		Uri sms = Uri.parse("smsto://" + phone);
		Intent intent = new Intent(android.content.Intent.ACTION_SENDTO, sms);
		context.startActivity(intent);
	}
}
