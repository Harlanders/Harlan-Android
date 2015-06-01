package com.harlan.libs.utils;

public class Dater {
	// 倒计时 CountDownTimer

	/**
	 * 把秒数转换成00：00：00
	 * 
	 * @param Seconds
	 * @return
	 */
	public static String seconds2HHMMSS(long SecondsSum) {
		int hour = 0;
		int minute = 0;
		int seconds = 0;

		hour = (int) (SecondsSum / 3600);
		SecondsSum %= 3600;
		minute = (int) (SecondsSum / 60);
		SecondsSum %= 60;
		seconds = (int) SecondsSum;

		String format = "%02d:%02d:%02d";
		return String.format(format, hour, minute, seconds);
	}
}
