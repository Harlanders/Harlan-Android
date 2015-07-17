package com.harlan.libs.volley;

import android.content.Context;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.harlan.libs.ui.utils.Toastor;

public class VolleyErrorListener implements ErrorListener {

	private Context context;

	public VolleyErrorListener(Context context) {
		this.context = context;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		Toastor.showShort(context, "网络异常，请稍后再试！");
	}

}
