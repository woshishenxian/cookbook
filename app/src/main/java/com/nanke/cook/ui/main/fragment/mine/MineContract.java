package com.nanke.cook.ui.main.fragment.mine;

import android.content.Context;

import com.nanke.cook.BasePresenter;
import com.nanke.cook.BaseView;
import com.nanke.cook.entity.weather.Data;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.entity.weather.WeatherData;
import com.nanke.cook.ui.BaseActivity;

/**
 * Created by vince on 16/11/3.
 */

public class MineContract {

    public interface View extends BaseView{
        public void loadWeahter(Data data);
    }

    public interface Presenter extends BasePresenter{
        public void getWeahter(String cityname);
        public void onMenuItemClick(Context context, int itemId);
    }
}
