package com.nanke.cook.ui.main.fragment.home;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;

import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.base.BaseView;
import com.nanke.cook.base.impl.BaseViewImpl;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.view.NavBaseDialog;
import com.nanke.cook.view.SearchBaseDialog;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class HomeContract {

    public interface View extends BaseView {

        void initToolbar();

        void loadCategory(List<Category> categories);

        void showSearchDialog();

        void startSearchActivity(String query);

        void startFoodsCollectedActivity();

        void startAboutActivity();

        void showWeatherDialog();

        void startWeatherActivity();


        void loadWeatherOnToday(Realtime realtime);

        void weatherRefreshError();

        void refreshWeather();

    }


    public interface Presenter extends BasePresenter{
        void getCategory(Context cotnext);

        void onMenuItemClick(MenuItem menuItem);

        void getWeatherOnToday(Context context);

        void onRequestPermissionsResult(Context context, int requestCode, int[] paramArrayOfInt);

        void requestLocationPermissions(Context context);

        android.view.View.OnClickListener getWeatherRefreshListener();

        android.view.View.OnClickListener getWeatherClickListener();

        NavBaseDialog.Callback getNavCallback();

        SearchBaseDialog.Callback getSearchCallback();
    }


}
