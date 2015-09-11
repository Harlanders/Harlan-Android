package com.harlan.libs.volley;

import java.io.UnsupportedEncodingException;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

public class FastjsonRequest<T> extends JsonRequest<T> {

	private Class<T> clz;

	public FastjsonRequest(int method, String url, JSONObject jsonRequest,
			Class<T> clz, Listener<T> listener, ErrorListener errorListener) {
		super(method, url, (jsonRequest == null) ? null : jsonRequest
				.toString(), listener, errorListener);
		this.clz = clz;
	}

	public FastjsonRequest(String url, JSONObject jsonRequest, Class<T> clz,
			Listener<T> listener, ErrorListener errorListener) {
		this(jsonRequest == null ? Method.GET : Method.POST, url, jsonRequest,
				clz, listener, errorListener);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			// Logger.i(json);
			return Response.success(JSON.parseObject(json, clz),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}
	}

}
