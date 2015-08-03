package com.harlan.libs.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Dater {
	// 倒计时 CountDownTimer

	public static final String TIMER_FORMAT = "%02d:%02d";
	public static final String YMD = "%04d-%02d-%02d";
	public static final String YM = "%04d-%02d";
	public static final String YW = "%04d-%02d";

	public enum Type {
		YEAR, MONTH, DAY
	}

	public enum DaterType {
		TIMER
	}

	/**
	 * 大于
	 */
	public static final int COMPARE_MORETHAN = 1;
	/**
	 * 等于
	 */
	public static final int COMPARE_EQUAL = 2;
	/**
	 * 小于
	 */
	public static final int COMPARE_LESSTHAN = 3;

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int seconds;

	public Dater(int year, int month, int day) {// 年月日
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public Dater(int hour, int minute, DaterType t) {
		this.hour = hour;// 时分秒
		this.minute = minute;
	}

	public Dater(int hour, int minute, int seconds, DaterType t) {
		this.hour = hour;// 时分秒
		this.minute = minute;
		this.seconds = seconds;
	}

	/**
	 * 根据格式输出当前时间年月日
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentYMD(String format) {
		return getYMD(getCurrentYear(), getCurrentMonth(), getCurrentDay());
	}

	/**
	 * 输出当前时间xxxx-xx-xx
	 * 
	 * @return
	 */
	public static String getCurrentYMD() {
		return getCurrentYMD(YMD);
	}

	/**
	 * 获取当前时间的dater
	 * 
	 * @return
	 */
	public static Dater getCurrentDater() {
		return new Dater(getCurrentYear(), getCurrentMonth(), getCurrentDay());
	}

	/**
	 * 根据输入年月日输出xxxx-xx-xx
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getYMD(int year, int month, int day) {
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		String ymd = String.format(Locale.getDefault(), YMD,
				gc.get(GregorianCalendar.YEAR),
				gc.get(GregorianCalendar.MONTH) + 1,
				gc.get(GregorianCalendar.DATE));
		return ymd;
	}

	/**
	 * 获取当月日期所在周
	 * 
	 * @return
	 */
	public int getWeekOfMonth() {
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		return gc.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 获取当月日期所在周
	 * 
	 * @return
	 */
	public static int getWeekOfMonth(int year, int month, int day) {
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		return gc.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 获取当前月日期所在周
	 * 
	 * @return
	 */
	public static int getCurrentWeekOfMonth() {
		GregorianCalendar gc = new GregorianCalendar(getCurrentYear(),
				getCurrentMonth() - 1, getCurrentDay());
		return gc.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 输出当前时间xxxx-xx
	 * 
	 * @return
	 */
	public static String getCurrentYM(String format) {
		return String.format(format, getCurrentYear(), getCurrentMonth());
	}

	/**
	 * 输出当前时间xxxx-xx
	 * 
	 * @return
	 */
	public static String getCurrentYM() {
		return getCurrentYM(YM);
	}

	/**
	 * 获取当前时间年份
	 * 
	 * @return
	 */
	public static int getCurrentYear() {
		return new GregorianCalendar().get(GregorianCalendar.YEAR);
	}

	/**
	 * 获取当前时间月份
	 * 
	 * @return
	 */
	public static int getCurrentMonth() {
		return new GregorianCalendar().get(GregorianCalendar.MONTH) + 1;
	}

	/**
	 * 获取当前时间日期
	 * 
	 * @return
	 */
	public static int getCurrentDay() {
		return new GregorianCalendar().get(GregorianCalendar.DATE);
	}

	/**
	 * 获取格式为xxxx-xx的年月
	 * 
	 * @return
	 */
	public String getDaterYM() {
		return getDaterYM(YM);
	}

	/**
	 * 获取年月
	 * 
	 * @return
	 */
	public String getDaterYM(String format) {
		return String.format(format, year, month);
	}

	/**
	 * 获取格式为xxxx-xx的年周
	 * 
	 * @return
	 */
	public String getDaterYW() {
		return getDaterYW(YW);
	}

	/**
	 * 获取年周
	 * 
	 * @return
	 */
	public String getDaterYW(String format) {
		return String.format(format, year, getDaterWeekOfYear());
	}

	/**
	 * 根据类型和偏移值计算日期
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param offset
	 * @return
	 */
	public static Dater getOffsetDater(int year, int month, int day,
			int offset, Type Type) {
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
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
				gc.get(GregorianCalendar.MONTH) + 1,
				gc.get(GregorianCalendar.DATE));
	}

	public static Dater getOffsetDater(Dater dater, int offset, Type Type) {
		return getOffsetDater(dater.getDaterYear(), dater.getDaterMonth(),
				dater.getDaterDay(), offset, Type);
	}

	/**
	 * 比较两个日期，dater2小于或等于dater1.返回false，反之则反； see{@link #compare2(Dater, Dater)}
	 * and {@link #equals(Object)}
	 * 
	 * @param dater1
	 * @param dater2
	 * @return
	 */
	@Deprecated
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
	 * 如果dater1大于dater2,则返回1,如果等于返回2，小于返回3
	 * 
	 * 
	 * @param dater1
	 * @param dater2
	 * @return
	 */
	public static int compare2(Dater dater1, Dater dater2) {

		if (dater1.getDaterYear() > dater2.getDaterYear()) {
			return COMPARE_MORETHAN;
		} else if (dater1.getDaterYear() == dater2.getDaterYear()) {
			if (dater1.getDaterMonth() > dater2.getDaterMonth()) {
				return COMPARE_MORETHAN;
			} else if (dater1.getDaterMonth() == dater2.getDaterMonth()) {
				if (dater1.getDaterDay() > dater2.getDaterDay()) {
					return COMPARE_MORETHAN;
				} else if (dater1.getDaterDay() == dater2.getDaterDay()) {
					return COMPARE_EQUAL;
				}
			}
		}
		return COMPARE_LESSTHAN;
	}

	/**
	 * 比较两个Dater是否相等
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Dater) {
			Dater dater2 = (Dater) o;
			if ((getDaterYear() == dater2.getDaterYear())
					&& (getDaterMonth() == dater2.getDaterMonth())
					&& (getDaterDay() == dater2.getDaterDay())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据类型计算两个日期差值
	 * 
	 * @return
	 */
	public static int differ(Dater dater1, Dater dater2, Type type) {
		int days = differ(dater1, dater2);
		switch (type) {
		case YEAR:
			return days / 30 / 12;
		case MONTH:
			return days / 30;
		case DAY:
			return days;
		}
		return -1;
	}

	/**
	 * 
	 * @param format
	 *            "%04d年%02d月第%02d周"
	 * @return
	 */
	public String getYMW(String format) {
		GregorianCalendar gc = new GregorianCalendar(getDaterYear(),
				getDaterMonth() - 1, getDaterDay());
		int week_of_month = gc.get(Calendar.WEEK_OF_MONTH);
		String ymw = String.format(Locale.getDefault(), format, getDaterYear(),
				getDaterMonth(), week_of_month);
		return ymw;

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

	public static String differ2(Dater dater1, Dater dater2) {
		long dater1Millis = getMillis(dater1);
		long dater2Millis = getMillis(dater2);
		long millis = Math.abs(dater1Millis - dater2Millis);
		int days = (int) (millis / 1000 / 3600 / 24);

		StringBuilder date = new StringBuilder();
		int year, month;
		if (days >= 365) {
			year = days / 365;
			days %= 365;
			date.append(year + "年");
		}
		if (days > 30) {
			month = days / 30;
			days %= 30;
			date.append(month + "月");
		}
		if (days > 0) {
			date.append(days + "日");
		}
		return date.toString();
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
	 * 获取指定年月的天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public int getDaterMonthDays() {
		GregorianCalendar gc = new GregorianCalendar(getDaterYear(),
				getDaterMonth() - 1, 1);
		gc.roll(GregorianCalendar.DATE, -1);
		return gc.get(GregorianCalendar.DATE);
	}

	/**
	 * 增加周数
	 * 
	 * @return
	 */
	public Dater addWeek(int offset) {
		GregorianCalendar gc = new GregorianCalendar(getDaterYear(),
				getDaterMonth() - 1, getDaterDay());
		gc.add(Calendar.WEEK_OF_YEAR, offset);
		setDater(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH) + 1,
				gc.get(Calendar.DATE));
		return this;
	}

	/**
	 * 增加1周
	 * 
	 * @return
	 */
	public Dater addOneWeek() {
		return addWeek(1);
	}

	/**
	 * 后退1周
	 * 
	 * @return
	 */
	public Dater backOneWeek() {
		return addWeek(-1);
	}

	/**
	 * 设置日期
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	public void setDater(int year, int month, int day) {
		setYear(year);
		setMonth(month);
		setDay(day);
	}

	public void setDater(Dater dater) {
		setDater(dater.getDaterYear(), dater.getDaterMonth(),
				dater.getDaterDay());
	}

	/**
	 * 获取当前周的星期天天数
	 * 
	 * @return
	 */
	public static int getCurrentSundayOfWeek() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return gc.get(Calendar.DATE);
	}

	/**
	 * 获取下一周星期天天数
	 * 
	 * @return
	 */
	public static int getNextSundayOfWeek() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		gc.add(Calendar.WEEK_OF_YEAR, 1);
		return gc.get(Calendar.DATE);
	}

	/**
	 * 获取当前日期所在周的星期天天数
	 * 
	 * @return
	 */
	public int getDaterSundayOfWeek() {
		GregorianCalendar gc = new GregorianCalendar(getDaterYear(),
				getDaterMonth() - 1, getDaterDay());
		gc.add(Calendar.WEEK_OF_YEAR, 1);// 一周
		gc.add(Calendar.WEEK_OF_YEAR, -1);// 一周
		gc.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return gc.get(Calendar.DATE);
	}

	/**
	 * 获取当前日期下一周的星期天天数
	 * 
	 * @return
	 */
	public int getDaterNextSundayOfWeek() {
		GregorianCalendar gc = new GregorianCalendar(getDaterYear(),
				getDaterMonth() - 1, getDaterDay());
		gc.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		gc.add(Calendar.WEEK_OF_YEAR, 1);
		return gc.get(Calendar.DATE);
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

	/**
	 * 获取当前周所有日期
	 * 
	 * @return
	 */
	public static int[] getCurrentDaysOfWeek() {
		int[] days = new int[7];
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		gc.add(Calendar.DAY_OF_MONTH, -1);

		for (int i = 0; i < 7; i++) {
			gc.add(Calendar.DAY_OF_MONTH, 1);
			days[i] = gc.get(Calendar.DAY_OF_MONTH);
		}
		return days;
	}

	/**
	 * 获取指定日期所在周所有日期
	 * 
	 * @return
	 */
	public static int[] getDaysOfWeek(int year, int month, int day) {
		int[] days = new int[7];
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		gc.get(Calendar.DAY_OF_WEEK);// 不能删除
		gc.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		gc.add(Calendar.DAY_OF_MONTH, -1);

		for (int i = 0; i < 7; i++) {
			gc.add(Calendar.DAY_OF_MONTH, 1);
			days[i] = gc.get(Calendar.DAY_OF_MONTH);
		}
		return days;
	}

	/**
	 * 获取指定日期所在周所有日期
	 * 
	 * @return
	 */
	public int[] getSpecialDaysOfWeek() {
		int[] days = new int[7];
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		gc.get(Calendar.DAY_OF_WEEK);// 不能删除
		gc.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		gc.add(Calendar.DAY_OF_MONTH, -1);

		for (int i = 0; i < 7; i++) {
			gc.add(Calendar.DAY_OF_MONTH, 1);
			if (gc.get(Calendar.MONTH) != (month - 1)) {
				days[i] = -1;
			} else {
				days[i] = gc.get(Calendar.DAY_OF_MONTH);
			}
		}
		return days;
	}

	/**
	 * 获取指定日期所在周所有日期
	 * 
	 * @return
	 */
	public Dater[] getDatersOfWeek() {
		Dater[] daters = new Dater[7];
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		gc.get(Calendar.DAY_OF_WEEK);// 不能删除
		gc.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		gc.add(Calendar.DAY_OF_MONTH, -1);

		for (int i = 0; i < 7; i++) {
			gc.add(Calendar.DAY_OF_MONTH, 1);
			daters[i] = new Dater(gc.get(Calendar.YEAR),
					gc.get(Calendar.MONTH) + 1, gc.get(Calendar.DATE));
		}
		return daters;
	}

	/**
	 * 获取日期在一年中所在周数
	 * 
	 * @return
	 */
	public int getDaterWeekOfYear() {
		return getWeekOfYear(year, month, day);
	}

	/**
	 * 获取指定日期在一年中所在周数
	 * 
	 * @return
	 */
	public static int getWeekOfYear(int year, int month, int day) {
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		return gc.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取当前日期在一年中所在周数
	 * 
	 * @return
	 */
	public static int getCurrentWeekOfYear() {
		GregorianCalendar gc = new GregorianCalendar();
		return gc.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取当前日期所在周所有日期
	 * 
	 * @return
	 */
	public int[] getDaterDaysOfWeek() {
		return getDaysOfWeek(getDaterYear(), getDaterMonth(), getDaterDay());
	}

	/**
	 * 按指定格式获取时分秒字符串
	 * 
	 * @param hour
	 * @param minute
	 * @param seconds
	 * @param format
	 * @return
	 */
	public static String timerFormat(int hour, int minute, int seconds,
			String format) {
		return String.format(format, hour, minute, seconds);
	}

	/**
	 * 按默认格式获取时分秒
	 * 
	 * @param hour
	 * @param minute
	 * @return 23：59：59 e.g.
	 */
	public static String timerFormat(int hour, int minute) {
		return String.format(Locale.getDefault(), TIMER_FORMAT, hour, minute);
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

	public String toDaterString() {
		return getYMD(year, month, day);
	}

	/**
	 * 字符串转换Dater
	 * 
	 * @param ymd
	 *            "1999-12-31"
	 * @return
	 */
	public static Dater YMD2Dater(String ymd) {
		try {
			String[] date = ymd.split("-");
			return new Dater(Integer.valueOf(date[0]),
					Integer.valueOf(date[1]), Integer.valueOf(date[2]));
		} catch (Exception e) {
			return null;
		}
	}
}
