package com.harlan.libs.ui.listener;

import android.widget.EditText;

/**
 * 限制小数点输入后两位
 */
public class Limit2DecimalTextWatcher extends SimpleTextWatcher {
	private EditText et;

	public Limit2DecimalTextWatcher(EditText et) {
		this.et = et;
	}

	public void onLimit2DecimalChanged(CharSequence s, int start, int before,
			int count) {
	};

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (s.toString().contains(".")) {
			if ((s.length() - 1 - s.toString().indexOf(".")) > 2) {
				s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
				et.setText(s);
				et.setSelection(s.length());
			}
		}
		if (s.toString().trim().substring(0).equals(".")) {
			s = "0" + s;
			et.setText(s);
			et.setSelection(2);
		}
		if (s.toString().startsWith("0") && (s.toString().trim().length() > 1)) {
			if (!s.toString().substring(1, 2).equals(".")) {
				et.setText(s.subSequence(0, 1));
				et.setSelection(1);
				return;
			}
		}

		onLimit2DecimalChanged(s, start, before, count);
	}
}
