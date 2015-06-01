package com.harlan.libs.utils;

import android.content.Intent;
import android.net.Uri;

import com.harlan.libs.core.Harlan;

public class Caller {
	public static void toPhoneUI(String phone) {
		Uri tel = Uri.parse("tel:" + phone);
		Intent intent = new Intent(Intent.ACTION_DIAL, tel);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Harlan.getContext().startActivity(intent);
	}

	public static void toSmsUI(String phone) {
		Uri sms = Uri.parse("smsto://" + phone);
		Intent intent = new Intent(android.content.Intent.ACTION_SENDTO, sms);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Harlan.getContext().startActivity(intent);
	}
}
