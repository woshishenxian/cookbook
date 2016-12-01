package com.nanke.cook.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.event.BusEvent;
import com.nanke.cook.utils.SnackbarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by vince on 16/10/25.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        initToolbar();

        presenter = initPresenter();

    }


    public void toast(String msg) {
        SnackbarUtils.show(this, msg);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN) //这种写法达到粘性的目的
    public void onBaseBusEvent(BusEvent event) {
        // UI updates must run on MainThread
    }

    protected void initToolbar() {

    }

    protected abstract int getLayoutView();

    protected abstract P initPresenter();

}
