package com.harlan.libs.utils;

import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

public class TextViewUtil {
	public static void setText(TextView tv, Object text) {
		if ((tv == null) || (text == null)) {
			Logger.i(tv.toString() + "or text cannont be null");
			return;
		}
		tv.setText(text.toString());
	}

	public static void setText(TextView tv, Spanned text) {
		if ((tv == null) || (text == null)) {
			Logger.i(tv.toString() + "or text cannont be null");
			return;
		}
		tv.setText(text);
	}

	/**
	 * 
	 * for instance (共计<font color=\"#ffffff\">123</font>车次)
	 * 
	 * @param tv
	 * @param text
	 * @param color
	 * @param prefix
	 *            前缀
	 * @param suffix
	 *            后缀
	 */
	public static void setText(TextView tv, Object text, String color,
			String prefix, String suffix) {
		if ((prefix == null) || (suffix == null)) {
			Logger.i("prefix or suffix cannon be null");
			return;
		}
		Spanned s = Html.fromHtml("(" + prefix + "<font color=\"" + color
				+ "\">" + text.toString() + "</font>" + suffix + ")");
		setText(tv, s);
	}
}
