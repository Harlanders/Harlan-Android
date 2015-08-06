package com.harlan.libs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtil {
	public static boolean isMobilePhone(String mobilePhone) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobilePhone);
		return m.matches();
	}

	public static boolean isTelephone(String telehone) {
		Pattern p1 = Pattern.compile("0\\d{2,3}-\\d{5,9}|0\\d{2,3}-\\d{5,9}"); // 验证带区号的
		Pattern p2 = Pattern.compile("0\\d{2,3}\\d{5,9}|0\\d{2,3}-\\d{5,9}"); // 验证带区号的
		return p1.matcher(telehone).matches() || p2.matcher(telehone).matches();
	}

	public static boolean isPhone(String phone) {
		return isMobilePhone(phone) || isTelephone(phone);
	}
}
