package com.harlan.libs.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Caller {
	public static void toPhoneUI(Context context, String phone) {
		Uri tel = Uri.parse("tel:" + phone);
		Intent intent = new Intent(Intent.ACTION_DIAL, tel);
		context.startActivity(intent);
	}

	public static void toSmsUI(Context context, String phone) {
		Uri sms = Uri.parse("smsto://" + phone);
		Intent intent = new Intent(android.content.Intent.ACTION_SENDTO, sms);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
}
