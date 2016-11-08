package com.nanke.cook.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.nanke.cook.R;
import com.nanke.cook.utils.LogUtils;

/**
 * Created by vince on 16/10/27.
 */

public class AutoSwipeRefreshLayout extends SwipeRefreshLayout {

    public AutoSwipeRefreshLayout(Context context) {
        super(context);
        init(context);
    }

    public AutoSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setColorSchemeColors(getColorPrimary(context));
    }

    private int getColorPrimary(Context context){
        TypedValue typedValue = new  TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    public void startRefreshing() {

        post(new Runnable() {
            @Override
            public void run() {
                if (!isRefreshing()) {
                    setRefreshing(true);
                }
            }
        });
    }

    public void stopRefreshing() {

        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRefreshing()) {
                    setRefreshing(false);
                }
            }
        }, 1000);
    }

}
