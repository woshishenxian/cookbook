package com.nanke.cook.ui.main;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.widget.AdapterView;

import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.base.BaseView;
import com.nanke.cook.entity.weather.Realtime;

/**
 * Created by vince on 16/11/11.
 */

public class MainContract {

    interface View extends BaseView{
        void initViewPager();

        void startAboutActivity();
    }

    interface Presenter extends BasePresenter{

        void onFBClick();

    }

}
