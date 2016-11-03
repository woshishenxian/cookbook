package com.nanke.cook.ui.main.fragment.mine;

import com.nanke.cook.BasePresenter;
import com.nanke.cook.BaseView;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.entity.weather.WeatherData;
import com.nanke.cook.ui.BaseActivity;

/**
 * Created by vince on 16/11/3.
 */

public class MineContract {

    public interface View extends BaseView{
        public void loadWeahter(Realtime realtime);
    }

    public interface Presenter extends BasePresenter{
        public void getWeahter(String cityname);
        public void onCalendarBtnClick(BaseActivity activity);
        public void onCollectBtnClick(BaseActivity activity);
    }
}
