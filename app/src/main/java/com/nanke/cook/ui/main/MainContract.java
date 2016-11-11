package com.nanke.cook.ui.main;

import android.support.design.widget.NavigationView;
import android.widget.AdapterView;

import com.flyco.dialog.widget.popup.BubblePopup;
import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.base.BaseView;
import com.nanke.cook.base.impl.BaseViewImpl;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.ui.weather.fragment.SublimePickerFragment;

/**
 * Created by vince on 16/11/11.
 */

public class MainContract {

    interface View extends BaseViewImpl{
        void initViewPager();

        void showCalendar();

        void startFoodsCollectedActivity();

        void startAboutActivity();

        void showThemeChoose();

        void showBubblePopup();

        void saveAndNotifyThemeChange(int position);

        void startWeatherActivity();

        void loadWeatherOnToday(Realtime realtime);

    }

    interface Presenter extends BasePresenter{

        void getWeatherOnToday(String cityname);

        android.view.View.OnClickListener getWeatherClickListener();

        AdapterView.OnItemClickListener getThemeChooseItemListener();

        NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener();


        SublimePickerFragment.Callback getSublimePickerCallback();
        void onFBClick();
    }

}
