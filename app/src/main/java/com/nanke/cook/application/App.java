package com.nanke.cook.application;

import android.app.Application;
import android.content.Context;

import com.nanke.cook.db.DBManager;
import com.nanke.cook.utils.LogUtils;

/**
 * Created by vince on 16/10/25.
 */

public class App extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        LogUtils.debug = true;
    }

    public static Context getContext() {
        return mContext;
    }
}
