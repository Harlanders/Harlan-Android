package com.harlan.libs.utils;

import android.text.Html;
import android.text.Spanned;

public class TextUtil {

	public static final String SPACE = " ";

	public static Spanned getColorText(String prefix, Object text,
			String color, String suffix) {
		prefix = prefix == null ? "" : prefix;
		suffix = suffix == null ? "" : suffix;
		Spanned s = Html.fromHtml(prefix + "<font color=\"" + color + "\">"
				+ text.toString() + "</font>" + suffix);
		return s;
	}
}
