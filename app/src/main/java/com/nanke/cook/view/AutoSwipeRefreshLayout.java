package com.nanke.cook.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.nanke.cook.R;
import com.nanke.cook.utils.LogUtils;

/**
 * Created by vince on 16/10/27.
 */

public class AutoSwipeRefreshLayout extends SwipeRefreshLayout{

    public AutoSwipeRefreshLayout(Context context) {
        super(context);
        init();
    }

    public AutoSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setColorSchemeResources(R.color.colorPrimary);
    }

    public void startRefreshing(){
        post(new Runnable() {
            @Override
            public void run() {
                setRefreshing(true);
            }
        });
    }

    public void stopRefreshing(){
        post(new Runnable() {
            @Override
            public void run() {
                setRefreshing(false);
            }
        });
    }



}
