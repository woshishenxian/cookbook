package com.nanke.cook.ui.main.fragment.home;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.nanke.cook.BasePresenter;
import com.nanke.cook.BaseView;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.main.MainActivity;
import com.nanke.cook.ui.weather.fragment.SublimePickerFragment;
import com.nanke.cook.view.swipefling.SwipeFlingAdapterView;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class HomeContract {

    public interface View extends BaseView {

        void initToolbar();

        void initNavigationView();

        void initDrawerLayout();

        void loadCategory(List<Category> categories);

        void loadWeatherToday(Realtime realtime);

        void showThemeChooseDialog();

        void turnToFoodsCollected();

        void showCalendar();

        void turnToAbout();

        void saveAndNotifyThemeChange(int position);

        void turnToWeather();

        void turnToSearch();
    }

    public interface Presenter extends BasePresenter{
        void getCategory(Context cotnext);

        void getWeatherToday(String cityname);

        boolean onNavigationItemSelected(MenuItem menuItem);

        void onThemeChooseItemClick(int position);

        void SublimePickerFragmentCallback();

        void onMenuItemClick(MenuItem menuItem);
    }


}
