package com.nanke.cook.ui.weather;

import android.content.Context;

import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.base.BaseView;
import com.nanke.cook.base.impl.BaseViewImpl;

/**
 * Created by vince on 16/11/21.
 */

public class WeatherContract {

    interface View extends BaseViewImpl{
        void loadWeatherWeb(String url);
    }

    interface Presenter extends BasePresenter{

        void getWeather(Context context);

    }
}
