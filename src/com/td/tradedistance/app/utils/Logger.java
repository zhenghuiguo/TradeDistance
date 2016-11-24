package com.td.tradedistance.app.utils;

import com.td.tradedistance.app.ApiGlobal;


/**
 * 日志输出管理
 */
public class Logger {

	private final static String TAG = "TradeDistance";

	/**
	 * Send an INFO log message
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void i(String tag, Object msg) {
		if (ApiGlobal.DEBUG) {
//			android.util.Log.i(TAG, tag + " == " + msg);
			android.util.Log.i(TAG, tag + "" + msg);
		}
	}

	/**
	 * Send an ERROR log message.
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void e(String tag, Object msg) {
		if (ApiGlobal.DEBUG) {
			android.util.Log.e(TAG, tag + " == " + msg);
		}
	}
	/**
	 * Send an ERROR log message.
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void w(String tag, Object msg) {
		if (ApiGlobal.DEBUG) {
			android.util.Log.e(TAG, tag + " == " + msg);
		}
	}
	/**
	 * Send a DEBUG log message
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void d(String tag, Object msg) {
		if (ApiGlobal.DEBUG) {
			android.util.Log.d(TAG, tag + " == " + msg);
		}
	}
	public static void d1(String tag, Object msg) {
		if (ApiGlobal.DEBUG) {
			android.util.Log.d(tag, " == " + msg);
		}
	}
	/**
	 * Send a DEBUG log message
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void d(Object msg) {
		if (ApiGlobal.DEBUG) {
			android.util.Log.d("URL:", String.valueOf(msg));
		}
	}
}