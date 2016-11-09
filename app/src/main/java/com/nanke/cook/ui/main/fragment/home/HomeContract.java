package com.nanke.cook.ui.main.fragment.home;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.nanke.cook.BasePresenter;
import com.nanke.cook.BaseView;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.main.MainActivity;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class HomeContract {

    public interface View extends BaseView {

        void loadCategory(List<Category> categories);

        void loadWeatherToday(Realtime realtime);
    }

    public interface Presenter extends BasePresenter{
        void getCategory(Context cotnext);

        void getWeatherToday(String cityname);

        void initToolbar(MainActivity mActivity, Toolbar toolbar, DrawerLayout mDrawerLayout);

        boolean onNavigationItemSelectedListener(MainActivity context,MenuItem menuItem);
    }


}
