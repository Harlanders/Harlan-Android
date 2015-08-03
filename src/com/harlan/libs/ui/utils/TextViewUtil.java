package com.harlan.libs.ui.utils;

import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.harlan.libs.utils.Logger;

/**
 * Ealisy use TextView,if <b>TextView</b> or <b>Text</b> is null,it will output
 * log msg
 * 
 * @author Harlan
 */
public class TextViewUtil {
	public static void setText(TextView tv, Object text) {
		if (check(tv, text)) {
			tv.setText(text.toString());
		}
	}

	public static void setText(TextView tv, Spanned text) {
		if (check(tv, text)) {
			tv.setText(text);
		}
	}

	/**
	 * 设置 for instance (共计<font color=\"#ffffff\">123</font>车次)
	 * 
	 * @param tv
	 * @param text
	 * @param color
	 * @param prefix
	 *            前缀
	 * @param suffix
	 *            后缀
	 */
	public static void setText(TextView tv, String prefix, Object text,
			String color, String suffix) {
		// if ((prefix == null) || (suffix == null)) {
		// Logger.i("prefix or suffix cannon be null");
		// return;
		// }
		prefix = prefix == null ? "" : prefix;
		suffix = suffix == null ? "" : suffix;
		Spanned s = Html.fromHtml(prefix + "<font color=\"" + color + "\">"
				+ text.toString() + "</font>" + suffix);
		setText(tv, s);
	}

	/**
	 * 设置TextView颜色后显示文字
	 * 
	 * @param tv
	 * @param text
	 * @param color
	 */
	public static void setColorText(TextView tv, Object text, int color) {
		if (check(tv, text)) {
			tv.setTextColor(color);
			tv.setText(text.toString());
		}
	}

	private static boolean check(TextView tv, Object text) {
		if ((tv == null) || (text == null)) {
			Logger.i(tv.toString() + "or text cannont be null");
			return false;
		}
		return true;
	}
}
