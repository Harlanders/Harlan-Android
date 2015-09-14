package com.harlan.libs.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.harlan.libs.BuildConfig;

/**
 * Log Manager,自动获取Tag
 * <p>
 * 单个字母方法为优化后的Log输出，双字母为原生Log输出。
 * 
 * @author Harlan
 * @data 2015-9-11 15:08:20
 * @email mikuad@gmail.com
 * @version 3
 */
public final class L {

	private static final String TAG = L.class.getSimpleName();

	private static boolean DEBUG = BuildConfig.DEBUG;

	private static final char TOP_LEFT_CORNER = '╔';
	private static final char BOTTOM_LEFT_CORNER = '╚';
	private static final char MIDDLE_CORNER = '╟';
	private static final char HORIZONTAL_DOUBLE_LINE = '║';

	private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
	private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";
	private static final String TOP_BORDER = "╔═════════════════════════════════════════════════════════════════════════════════════════════";
	private static final String BOTTOM_BORDER = "╚═════════════════════════════════════════════════════════════════════════════════════════════";
	private static final String MIDDLE_BORDER = "╟─────────────────────────────────────────────────────────────────────────────────────────────";
	private static final String LINE = "─────────────────────────────────────────────────────────────────────────────────────────────";
	private static final String NEW_LINE = "\n";
	private static final String AT = "at ";
	private static final String POINT = "▪";
	private static final String BLANK = "  ";

	/**
	 * Android's max limit for a log entry is ~4076 bytes, so 4000 bytes is used
	 * as chunk size since default charset is UTF-8
	 */
	private static final int CHUNK_SIZE = 4000;

	private L() {
	}

	/**
	 * Get the TAG.
	 */
	private static String getTag() {
		StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[5];

		String className = stackTrace.getClassName();
		className = className.substring(className.lastIndexOf(".") + 1);

		String format = "%s.%s(L:%d)";
		String tag = String.format(format, className,
				stackTrace.getMethodName(), stackTrace.getLineNumber());
		return tag;
	}

	/**
	 * Generates for clickable link.
	 */
	private static String callMethodAndLine() {
		StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[4];
		StringBuilder sb = new StringBuilder();
		sb.append(AT).append(thisMethodStack.getClassName() + ".")
				.append(thisMethodStack.getMethodName())
				.append("(" + thisMethodStack.getFileName())
				.append(":" + thisMethodStack.getLineNumber() + ")  ");
		return sb.toString();
	}

	/**
	 * Uses optimizational Log to output log.
	 */
	private static String getLMsg(Object... msg) {
		StringBuilder sb = new StringBuilder();
		sb.append(TOP_BORDER);
		sb.append(NEW_LINE);
		sb.append(HORIZONTAL_DOUBLE_LINE);
		sb.append(BLANK + callMethodAndLine());
		sb.append(NEW_LINE);
		sb.append(MIDDLE_BORDER);// 1
		sb.append(NEW_LINE);
		// sb.append(HORIZONTAL_DOUBLE_LINE);

		for (Object element : msg) {
			if (element == null) {
				return "Object is null!";
			}
			String mark = "";
			if (msg.length != 1) {
				mark = POINT;
			}

			String message = getString(element);
			if (message.contains(NEW_LINE)) {
				message = message.replaceAll(NEW_LINE, NEW_LINE
						+ HORIZONTAL_DOUBLE_LINE + BLANK);
			}

			sb.append(HORIZONTAL_DOUBLE_LINE);
			sb.append(BLANK);
			sb.append(mark);
			sb.append(message);
			sb.append(NEW_LINE);
		}// 2
		sb.append(BOTTOM_BORDER);// 3
		return sb.toString();
	}

	/**
	 * Generates a string accroiding to different object.
	 */
	private static String getString(Object obj) {
		if (obj.getClass().isArray()) {
			return array(obj);
		} else if (obj instanceof Map) {
			return map(obj);
		} else if (obj instanceof Collection) {
			return collection(obj);
		} else {
			// return obj.toString();
			return obj(obj);
		}
	}

	/**
	 * Object
	 */
	private static String obj(Object obj) {
		final String simpleName = obj.getClass().getSimpleName();
		// return simpleName + " { " + obj.toString() + " }";
		return simpleName + " : " + obj.toString();
	}

	/**
	 * Collection
	 */
	private static String collection(Object obj) {
		final String simpleName = obj.getClass().getSimpleName();
		Collection collection = (Collection) obj;
		String msg = "%s size = %d [\n";
		msg = String.format(msg, simpleName, collection.size());
		if (!collection.isEmpty()) {
			Iterator<Object> iterator = collection.iterator();
			int flag = 0;
			while (iterator.hasNext()) {
				String itemString = BLANK + "[%d] : %s%s";
				Object item = iterator.next();
				msg += String.format(itemString, flag++,
						SystemUtil.objectToString(item), "\n");
				// flag++ < (collection.size() - 1) ? ",\n" :
				// "\n");
			}
		}
		return msg + "]";
	}

	/**
	 * Array
	 */
	private static String array(Object obj) {
		final String simpleName = obj.getClass().getSimpleName();
		String msg = "Temporarily not support more than two dimensional Array!";
		int dim = ArrayUtil.getArrayDimension(obj);
		switch (dim) {
		case 1:
			Pair pair = ArrayUtil.arrayToString(obj);
			msg = simpleName.replace("[]", "[" + pair.first + "] {\n" + BLANK);
			msg += pair.second + "\n";
			break;
		case 2:
			Pair pair1 = ArrayUtil.arrayToObject(obj);
			Pair pair2 = (Pair) pair1.first;
			msg = simpleName.replace("[][]", "[" + pair2.first + "]["
					+ pair2.second + "] {\n");
			msg += pair1.second;
			msg = msg.replaceAll(NEW_LINE + "\\[", NEW_LINE + BLANK + "\\[");
			break;
		default:
			break;
		}
		return msg + "}";
	}

	/**
	 * Json
	 */
	public static void j(String json) {
		if (TextUtils.isEmpty(json)) {
			i("Empty/Null json content");
			return;
		}
		try {
			if (json.startsWith("{")) {
				JSONObject jsonObject = new JSONObject(json);
				String message = jsonObject.toString(4);
				log(Log.INFO, true, message);
				return;
			}
			if (json.startsWith("[")) {
				JSONArray jsonArray = new JSONArray(json);
				String message = jsonArray.toString(4);
				log(Log.INFO, true, message);
			}
		} catch (JSONException e) {
			log(Log.INFO, true, e.getCause().getMessage() + "\n" + json);
		}
	}

	/**
	 * List Json
	 */
	// public static void lj(List<String> l) {
	// for (String string : l) {
	// j(string);
	// }
	// }

	/**
	 * Map
	 */
	private static String map(Object object) {
		final String simpleName = object.getClass().getSimpleName();
		String msg = simpleName + " {\n";
		Map<Object, Object> map = (Map<Object, Object>) object;
		Set<Object> keys = map.keySet();
		for (Object key : keys) {
			String itemString = BLANK + "[ %s -> %s ]\n";
			Object value = map.get(key);
			msg += String.format(itemString, SystemUtil.objectToString(key),
					SystemUtil.objectToString(value));
		}
		return msg + "}";
	}

	/**
	 * Uses original Log to output log.
	 */
	private static String getMsg(Object... msgs) {
		StringBuilder sb = new StringBuilder();
		for (Object object : msgs) {
			sb.append(object.toString()).append("\n");
		}
		return sb.toString();
	}

	private static void log(int log_id, Boolean useL, Object... msgs) {
		if (DEBUG) {
			String tag = getTag();
			String msg;
			if (useL) {
				msg = getLMsg(msgs);
			} else {
				msg = getMsg(msgs);
			}

			switch (log_id) {
			case Log.VERBOSE:
				Log.v(tag, msg);
				break;
			case Log.DEBUG:
				Log.d(tag, msg);
				break;
			case Log.INFO:
				Log.i(tag, msg);
				break;
			case Log.WARN:
				Log.w(tag, msg);
				break;
			case Log.ERROR:
				Log.e(tag, msg);
				break;
			// case Log.ASSERT:
			// Log.wtf(tag, msg);
			// break;
			}
		}
	}

	public static void v(Object... msg) {
		log(Log.VERBOSE, true, msg);
	}

	public static void d(Object... msg) {
		log(Log.DEBUG, true, msg);
	}

	public static void i(Object... msg) {
		log(Log.INFO, true, msg);
	}

	public static void w(Object... msg) {
		log(Log.WARN, true, msg);
	}

	public static void e(Object... msg) {
		log(Log.ERROR, true, msg);
	}

	public static void vv(Object... msg) {
		log(Log.VERBOSE, false, msg);
	}

	public static void dd(Object... msg) {
		log(Log.DEBUG, false, msg);
	}

	public static void ii(Object... msg) {
		log(Log.INFO, false, msg);
	}

	public static void ww(Object... msg) {
		log(Log.WARN, false, msg);
	}

	public static void ee(Object... msg) {
		log(Log.ERROR, false, msg);
	}
}
