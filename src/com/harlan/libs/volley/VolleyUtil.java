package com.harlan.libs.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.harlan.libs.core.ApplicationManager;

public class VolleyUtil {

	public static RequestQueue queue = Volley
			.newRequestQueue(ApplicationManager.getContext());

	public static void addRequest(Request<?> request) {
		queue.add(request);
	}

	public static void addRequest(Request<?>... request) {
		for (Request<?> r : request) {
			addRequest(r);
		}
	}

}
