/**
 * @Description: TODO(日志工具)
 * @author A18ccms A18ccms_gmail_com   
 * @date 2016-8-26 下午4:48:02 
 **/
package com.nanke.cook.utils;

import android.util.Log;

/**
 * @author vince
 * 
 */
public class LogUtils {

	public static boolean debug = true;

	public static void i(String tag, String msg) {
		if (debug)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg) {
		if (debug)
			Log.d(tag, msg);
	}

	public static void e(String tag, String msg) {
		if (debug)
			Log.e(tag, msg);
	}

	public static void w(String tag, String msg) {
		if (debug)
			Log.w(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (debug)
			Log.v(tag, msg);
	}

	public static void wtf(String tag, String msg) {
		if (debug)
			Log.wtf(tag, msg);
	}
}
