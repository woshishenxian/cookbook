package com.nanke.cook.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap.Config;

public class PreferenceUtil {
	public static final String USER_CONFIG = "USER_INFO";

	private PreferenceUtil() {
		throw new AssertionError();
	}

	/**
	 * 首次使用设置
	 * 
	 * @author
	 * 
	 */
	public static class IntroConfig {
		public static final String INTRO_CONFIG = "intro_config"; // 首次开启app
		public static final String COUPON_CONFIG = "coupon_config";// 首次开启优惠券

		public static final String CONFIG_VALUE_IS = "intro_config_value_is";
		public static final String CONFIG_VALUE_NO = "intro_config_value_no";

		public static void putIntroCfg(Context context, String content) {
			putString(context, INTRO_CONFIG, content);
		}

		public static String getIntroCfg(Context context) {
			return getString(context, INTRO_CONFIG, CONFIG_VALUE_IS);
		}

		public static void putCoupCfg(Context context, String content) {
			putString(context, COUPON_CONFIG, content);
		}

		public static String getCoupCfg(Context context) {
			return getString(context, COUPON_CONFIG, CONFIG_VALUE_IS);
		}
	}

	/**
	 * 用户登录设置
	 * 
	 * @author
	 * 
	 */
	public static class LoginConfig {

		public static final String KEEP_AUTO_KEY = "auto_login";
		public static final String LOGIN_NAME = "loginName";
		public static final String LOGIN_PWD = "loginPwd";
		public static final String LOGIN_ID = "loginid";
		public static final String KEEP_LOGIN = "is_login";

		/**
		 * 是否自动登录
		 * 
		 * @param context
		 * @return
		 */
		public static boolean isAutoLogin(Context context) {
			return getBoolean(context, KEEP_AUTO_KEY, false);
		}

		public static void setAutoLogin(Context context, boolean isAtuo) {
			putBoolean(context, KEEP_AUTO_KEY, isAtuo);
		}

		/**
		 * 是否已经登录
		 * 
		 * @param context
		 * @return
		 */

		public static boolean isLogin(Context context) {
			return getBoolean(context, KEEP_LOGIN, false);
		}

		public static void setKeepLogin(Context context, boolean isAtuo) {
			putBoolean(context, KEEP_LOGIN, isAtuo);
		}
	}



	/**
	 * put boolean preferences
	 * 
	 * @param context
	 * @param key
	 *            The name of the preference to modify
	 * @param value
	 *            The new value for the preference
	 * @return True if the new values were successfully written to persistent
	 *         storage.
	 */
	public static boolean putBoolean(Context context, String key, boolean value) {
		SharedPreferences settings = context.getSharedPreferences(USER_CONFIG,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(key, value);
		return editor.commit();
	}

	/**
	 * get boolean preferences
	 * 
	 * @param context
	 * @param key
	 *            The name of the preference to retrieve
	 * @param defaultValue
	 *            Value to return if this preference does not exist
	 * @return The preference value if it exists, or defValue. Throws
	 *         ClassCastException if there is a preference with this name that
	 *         is not a boolean
	 */
	public static boolean getBoolean(Context context, String key,
			boolean defaultValue) {
		SharedPreferences settings = context.getSharedPreferences(USER_CONFIG,
				Context.MODE_PRIVATE);
		return settings.getBoolean(key, defaultValue);

	}

	/**
	 * put string preferences
	 * 
	 * @param context
	 * @param key
	 *            The name of the preference to modify
	 * @param value
	 *            The new value for the preference
	 * @return True if the new values were successfully written to persistent
	 *         storage.
	 */
	public static boolean putString(Context context, String key, String value) {
		SharedPreferences settings = context.getSharedPreferences(USER_CONFIG,
				Context.MODE_MULTI_PROCESS);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, value);
		return editor.commit();
	}

	/**
	 * get string preferences
	 * 
	 * @param context
	 * @param key
	 *            The name of the preference to retrieve
	 * @param defaultValue
	 *            Value to return if this preference does not exist
	 * @return The preference value if it exists, or defValue. Throws
	 *         ClassCastException if there is a preference with this name that
	 *         is not a string
	 */
	public static String getString(Context context, String key,
			String defaultValue) {
		SharedPreferences settings = context.getSharedPreferences(USER_CONFIG,
				Context.MODE_MULTI_PROCESS);
		return settings.getString(key, defaultValue);
	}
}
