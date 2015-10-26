package com.harlan.libs.volley;

import java.io.UnsupportedEncodingException;

import org.json.JSONObject;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.harlan.libs.utils.L;

public class GsonRequest<T> extends JsonRequest<T> {

	private Class<T> clz;

	public GsonRequest(int method, String url, JSONObject jsonRequest,
			Class<T> clz, Listener<T> listener, ErrorListener errorListener) {
		super(method, url, (jsonRequest == null) ? null : jsonRequest
				.toString(), listener, errorListener);
		this.clz = clz;
	}

	public GsonRequest(String url, JSONObject jsonRequest, Class<T> clz,
			Listener<T> listener, ErrorListener errorListener) {
		this(jsonRequest == null ? Method.GET : Method.POST, url, jsonRequest,
				clz, listener, errorListener);
	}

	public GsonRequest(String url, Class<T> clz, Listener<T> listener,
			ErrorListener errorListener) {
		this(url, null, clz, listener, errorListener);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			L.i(json);
			return Response.success(new Gson().fromJson(json, clz),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}
	}

}
