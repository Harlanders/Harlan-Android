package com.harlan.libs.utils;

import java.text.DecimalFormat;

/**
 * decimal util
 * 
 * @author Harlan
 * @date 2015年7月20日 14:43:08
 */
public class Fomatter {

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

}
