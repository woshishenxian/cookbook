package com.nanke.cook.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nanke.cook.BasePresenter;
import com.nanke.cook.event.BusEvent;
import com.nanke.cook.utils.SnackbarUtils;
import com.nanke.cook.utils.ThemeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by vince on 16/10/25.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        presenter = initPresenter();
        initToolbar();
    }


    public void toast(String msg) {
        SnackbarUtils.show(this,msg);
    }


    public void reload(boolean anim) {
        Intent intent = getIntent();
        if (!anim) {
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        finish();
        if (!anim) {
            overridePendingTransition(0, 0);
        }
        startActivity(intent);
        recreate();

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN) //这种写法达到粘性的目的
    public void onMainBusEvent(BusEvent event) {
// UI updates must run on MainThread
        if(event.getType() == BusEvent.TYPE_CHANGE_THEME){
            reload(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initTheme(){
        ThemeUtils.Theme theme = ThemeUtils.getCurrentTheme(this);
        ThemeUtils.changeTheme(this, theme);
    }

    public abstract int getLayoutView();
    public abstract T initPresenter();
    public abstract void initToolbar();
}
