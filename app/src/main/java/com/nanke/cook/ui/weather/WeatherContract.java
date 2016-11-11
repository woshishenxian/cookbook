package com.nanke.cook.ui.weather;

import android.content.Context;

import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.base.BaseView;
import com.nanke.cook.base.impl.BaseViewImpl;
import com.nanke.cook.entity.weather.Data;

/**
 * Created by vince on 16/11/3.
 */

public class WeatherContract {

    public interface View extends BaseViewImpl {
        public void loadWeahter(Data data);
    }

    public interface Presenter extends BasePresenter{
        public void getWeahter(String cityname);
        public void onMenuItemClick(Context context, int itemId);
    }
}
