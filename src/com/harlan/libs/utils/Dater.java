package com.harlan.libs.utils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Dater {
	// 倒计时 CountDownTimer

	public static final String YMD = "%04d-%02d-%02d";
	public static final String YM = "%04d-%02d";

	public enum Type {
		YEAR, MONTH, DAY
	}

	public enum DaterType {
		TIMER
	}

	private int year;
	private int month;
	private int day;

	public Dater(int year, int month, int day) {// 年月日
		this.year = year;
		this.month = month;
		this.day = day;
	}

	private int hour;
	private int minute;
	private int seconds;

	public Dater(int hour, int minute, DaterType t) {
		this.hour = hour;// 时分秒
		this.minute = minute;
	}

	public Dater(int hour, int minute, int seconds, DaterType t) {
		this.hour = hour;// 时分秒
		this.minute = minute;
		this.seconds = seconds;
	}

	public static String getCurrentYMD(String format) {
		return getYMD(getCurrentYear(), getCurrentMonth(), getCurrentDay());
	}

	public static String getCurrentYMD() {
		return getCurrentYMD(YMD);
	}

	public static Dater getCurrentDater() {
		return new Dater(getCurrentYear(), getCurrentMonth(), getCurrentDay());
	}

	public static String getYMD(int year, int month, int day) {
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		String ymd = String.format(Locale.getDefault(), YMD,
				gc.get(GregorianCalendar.YEAR),
				gc.get(GregorianCalendar.MONTH) + 1,
				gc.get(GregorianCalendar.DATE));
		return ymd;
	}

	public static int getWeekOfMonth(int year, int month, int day) {
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		return gc.get(Calendar.WEEK_OF_MONTH);
	}

	public static int getCurrentWeekOfMonth() {
		GregorianCalendar gc = new GregorianCalendar(getCurrentYear(),
				getCurrentMonth() - 1, getCurrentDay());
		return gc.get(Calendar.WEEK_OF_MONTH);
	}

	public static String getYM(String format) {
		return String.format(format, getCurrentYear(), getCurrentMonth());
	}

	public static String getYM() {
		return getYM(YM);
	}

	public static int getCurrentYear() {
		return new GregorianCalendar().get(GregorianCalendar.YEAR);
	}

	public static int getCurrentMonth() {
		return new GregorianCalendar().get(GregorianCalendar.MONTH) + 1;
	}

	public static int getCurrentDay() {
		return new GregorianCalendar().get(GregorianCalendar.DATE);
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param offset
	 * @return
	 */
	public static Dater getOffsetDater(int year, int month, int day,
			int offset, Type Type) {
		GregorianCalendar gc = new GregorianCalendar(year, month, day);
		switch (Type) {
		case YEAR:
			gc.add(GregorianCalendar.YEAR, offset);
			break;
		case MONTH:
			gc.add(GregorianCalendar.MONTH, offset);
			break;
		case DAY:
			gc.add(GregorianCalendar.DATE, offset);
			break;
		}
		return new Dater(gc.get(GregorianCalendar.YEAR),
				gc.get(GregorianCalendar.MONTH), gc.get(GregorianCalendar.DATE));
	}

	public static Dater getOffsetDater(Dater dater, int offset, Type Type) {
		return getOffsetDater(dater.getDaterYear(), dater.getDaterMonth(),
				dater.getDaterDay(), offset, Type);
	}

	/**
	 * 比较两个日期，dater2小于或等于dater1.返回false，反之则反；
	 * 
	 * @param dater1
	 * @param dater2
	 * @return
	 */
	public static boolean compare(Dater dater1, Dater dater2) {
		if (dater2.getDaterYear() < dater1.getDaterYear()) {
			return false;
		}

		if (dater2.getDaterYear() == dater1.getDaterYear()) {
			if (dater2.getDaterMonth() < dater1.getDaterMonth()) {
				return false;
			} else if (dater2.getDaterMonth() == dater1.getDaterMonth()) {
				if (dater2.getDaterDay() <= dater1.getDaterDay()) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 根据类型计算两个日期差值
	 * 
	 * @return
	 */
	public static double differ(Dater dater1, Dater dater2, Type type) {
		long dater1Millis = getMillis(dater1);
		long dater2Millis = getMillis(dater2);
		long millis = Math.abs(dater1Millis - dater2Millis);
		double days = millis / 1000 / 3600 / 24;
		switch (type) {
		case YEAR:
			return Double.parseDouble(new DecimalFormat("0.0").format(days));
		case MONTH:
			return (int) days / 12;
		case DAY:
			return (int) days;
		}
		return -1;
	}

	/**
	 * 根据类型计算两个日期差值
	 * 
	 * @return
	 */
	public static int differ(Dater dater1, Dater dater2) {
		long dater1Millis = getMillis(dater1);
		long dater2Millis = getMillis(dater2);
		long millis = Math.abs(dater1Millis - dater2Millis);
		int days = (int) (millis / 1000 / 3600 / 24);
		return days;
	}

	/**
	 * 获取日期的UNIX时间戳
	 * 
	 * @param dater
	 * @return
	 */
	public static long getMillis(Dater dater) {
		GregorianCalendar gc = new GregorianCalendar(dater.getDaterYear(),
				dater.getDaterMonth(), dater.getDaterDay());
		long millis = gc.getTimeInMillis();
		return millis;
	}

	/**
	 * 获取指定年月的天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMonthDays(int year, int month) {
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, 1);
		gc.roll(GregorianCalendar.DATE, -1);
		return gc.get(GregorianCalendar.DATE);
	}

	/**
	 * 获取当前年月的天数
	 * 
	 * @return
	 */
	public static int getCurrentMonthDays() {
		return getMonthDays(getCurrentYear(), getCurrentMonth());
	}

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

	public static String timerFormat(int hour, int minute, int seconds,
			String format) {
		return String.format(format, hour, minute, seconds);
	}

	public static final String TIMER_FORMAT = "%02d:%02d";

	public static String timerFormat(int hour, int minute) {
		return String.format(TIMER_FORMAT, hour, minute);
	}

	public int getDaterYear() {
		return year;
	}

	public int getDaterMonth() {
		return month;
	}

	public int getDaterDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	@Override
	@Deprecated
	public String toString() {
		return getYMD(year, month, day);
	}

	public String toDaterString() {
		return getYMD(year, month, day);
	}

	// public String toTimerString() {
	// return "";
	// }
}
