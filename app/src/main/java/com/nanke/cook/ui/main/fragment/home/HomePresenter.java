package com.nanke.cook.ui.main.fragment.home;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.nanke.cook.R;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.entity.weather.Weather;
import com.nanke.cook.source.ArrCallBack;
import com.nanke.cook.source.FoodsDataRepository;
import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.source.WeatherDataRepository;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.collect.FoodsCollectedActivity;
import com.nanke.cook.ui.main.BackPressedInterface;
import com.nanke.cook.ui.main.MainActivity;
import com.nanke.cook.ui.weather.fragment.SublimePickerFragment;

import java.util.List;

import static com.nanke.cook.R.id.mDrawerLayout;

/**
 * Created by vince on 16/10/25.
 */

public class HomePresenter implements HomeContract.Presenter,BackPressedInterface{

    FoodsDataRepository foodsDataRepository;
    WeatherDataRepository weatherDataRepository;
    HomeContract.View view;
    DrawerLayout mDrawerLayout;

    public HomePresenter(HomeContract.View view) {
        this.foodsDataRepository = new FoodsDataRepository();
        this.weatherDataRepository = new WeatherDataRepository();
        this.view = view;
    }

    @Override
    public void getCategory(Context cotnext) {
        foodsDataRepository.getCategory(cotnext, categoryArrCallBack);
    }


    @Override
    public void initToolbar(MainActivity mActivity, Toolbar toolbar, DrawerLayout mDrawerLayout) {
        this.mDrawerLayout = mDrawerLayout;
        toolbar.inflateMenu(R.menu.menu_main_more);
        SearchManager searchManager =
                (SearchManager) mActivity.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(toolbar.getMenu().findItem(R.id.search));
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(mActivity.getComponentName()));
        searchView.setQueryHint(mActivity.getString(R.string.search_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                recyclerAdapter.getFilter().filter(s);
                return true;
            }
        });
//        MenuItemCompat.setOnActionExpandListener(searchItem, mainPresenter);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                mActivity,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.app_name,  /* "open drawer" description for accessibility */
                R.string.app_name  /* "close drawer" description for accessibility */
        );
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mActivity.setmBackPressedInterface(this);
    }

    @Override
    public void getWeatherToday(String cityName) {
        weatherDataRepository.getWeather(cityName,weatherObjCallBack);
    }

    @Override
    public boolean onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelectedListener(MainActivity context,MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.btn_calendar:
                onCalendarBtnClick(context);
            break;
            case R.id.btn_collect_center:
                context.startActivity(new Intent(context, FoodsCollectedActivity.class));
            break;
            case R.id.btn_theme:

            break;
            case R.id.btn_about:

            break;
        }
        return true;
    }


    private void onCalendarBtnClick(MainActivity activity) {

        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(mFragmentCallback);

        SublimeOptions options = new SublimeOptions();
        options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER);
        options.setDisplayOptions(1);

        // Enable/disable the date range selection feature
        options.setCanPickDateRange(true);
        // Valid options
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", options);
        pickerFrag.setArguments(bundle);

        pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.show(activity.getSupportFragmentManager(), "SUBLIME_PICKER");

    }




    private ArrCallBack<Category> categoryArrCallBack = new ArrCallBack<Category>() {
        @Override
        public void onTasksLoaded(List<Category> tasks) {
            view.loadCategory(tasks);
        }

        @Override
        public void onDataNotAvailable(String msg) {

        }

        @Override
        public void start() {
            view.showLoading();
        }

        @Override
        public void onComplete() {
            view.hideLoading();
        }
    };

    private ObjCallBack<Realtime> weatherObjCallBack = new ObjCallBack<Realtime>() {
        @Override
        public void onTasksLoaded(Realtime tasks) {
            view.loadWeatherToday(tasks);
        }

        @Override
        public void onDataNotAvailable(String msg) {
            view.onError(msg);
        }

        @Override
        public void start() {
            view.showLoading();
        }

        @Override
        public void onComplete() {
            view.hideLoading();
        }
    };


    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {

        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {

        }
    };
}
