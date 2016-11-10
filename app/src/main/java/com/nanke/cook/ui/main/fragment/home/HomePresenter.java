package com.nanke.cook.ui.main.fragment.home;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.nanke.cook.R;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.source.ArrCallBack;
import com.nanke.cook.source.FoodsDataRepository;
import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.source.WeatherDataRepository;
import com.nanke.cook.ui.weather.fragment.SublimePickerFragment;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class HomePresenter implements HomeContract.Presenter {

    FoodsDataRepository foodsDataRepository;
    WeatherDataRepository weatherDataRepository;
    HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.foodsDataRepository = new FoodsDataRepository();
        this.weatherDataRepository = new WeatherDataRepository();
        this.view = view;
        this.view.initToolbar();
        this.view.initDrawerLayout();
        this.view.initNavigationView();
    }

    @Override
    public void getCategory(Context cotnext) {
        foodsDataRepository.getCategory(cotnext, categoryArrCallBack);
    }


    @Override
    public void getWeatherToday(String cityName) {
        weatherDataRepository.getWeather(cityName, weatherObjCallBack);
    }

    @Override
    public NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener() {
        return onNavigationItemSelectedListener;
    }

    @Override
    public SublimePickerFragment.Callback getSublimePickerFragmentCallback() {
        return mFragmentCallback;
    }

    @Override
    public AdapterView.OnItemClickListener getThemeChooseItemListener() {
        return onThemeChoose;
    }


    //分类数据回调
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

    //天气数据回调
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

    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.btn_calendar:
                    view.showCalendar();
                    break;
                case R.id.btn_collect_center:
                    view.turnToFoodsCollected();
                    break;
                case R.id.btn_theme:
                    view.showThemeChooseDialog();
                    break;
                case R.id.btn_about:
                    view.turnToAbout();
                    break;
            }
            return true;
        }
    };

    private AdapterView.OnItemClickListener onThemeChoose = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            view.saveAndNotifyThemeChange(position);
        }
    };


    private SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {

        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {

        }
    };

}
