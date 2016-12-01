package com.nanke.cook.ui.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.nanke.cook.R;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.source.WeatherDataRepository;
import com.nanke.cook.utils.PermissionM;

/**
 * Created by vince on 16/11/11.
 */

public class MainPresenter implements MainContract.Presenter {


    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.view.initViewPager();
    }



    @Override
    public void onFBClick() {
        view.startAboutActivity();
    }


}
