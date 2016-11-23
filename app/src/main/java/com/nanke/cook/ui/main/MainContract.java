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

        void startFoodsCollectedActivity();

        void startAboutActivity();

        void showThemeChoose();

        void showBubblePopup();

        void saveAndNotifyThemeChange(int position);

        void startWeatherActivity();

        void loadWeatherOnToday(Realtime realtime);

        void weatherRefreshError();

        void refreshWeather();
    }

    interface Presenter extends BasePresenter{

        void getWeatherOnToday(Context context);

        android.view.View.OnClickListener getWeatherRefreshListener();

        android.view.View.OnClickListener getWeatherClickListener();

        AdapterView.OnItemClickListener getThemeChooseItemListener();

        NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener();

        void onFBClick();
    }

}
