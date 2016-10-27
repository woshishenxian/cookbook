package com.nanke.cook.application;

import android.app.Application;
import android.content.Context;

import com.nanke.cook.db.DBManager;

/**
 * Created by vince on 16/10/25.
 */

public class App extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
//        DBManager.getInstance();
    }

    public static Context getContext() {
        return mContext;
    }
}
