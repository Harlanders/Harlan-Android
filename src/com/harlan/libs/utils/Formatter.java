package com.harlan.libs.utils;

import java.text.DecimalFormat;

/**
 * decimal util
 * 
 * @author Harlan
 * @date 2015年7月20日 14:43:08
 */
public class Formatter {

	public static final String TWO_DECIMAL = "0.00";

	public static String decimalFormat(String pattern, double value) {
		DecimalFormat format = new DecimalFormat(pattern);
		return format.format(value);
	}

	/**
	 * Limit of two decimal
	 * 
	 * @param value
	 * @return
	 */
	public static String twoDecimal(double value) {
		return decimalFormat(TWO_DECIMAL, value);
	}

	/**
	 * Limit of two decimal
	 * 
	 * @param value
	 * @return
	 */
	public static double twoDecimal2Double(double value) {
		return Double.parseDouble(decimalFormat(TWO_DECIMAL, value));
	}

	/**
	 * double为小数为0则返回整数，否则不变
	 * 
	 * @param num
	 * @return
	 */
	public static String doubleTrans(double num) {
		if ((Math.round(num) - num) == 0) {
			return String.valueOf((long) num);
		}
		return String.valueOf(num);
	}
}
