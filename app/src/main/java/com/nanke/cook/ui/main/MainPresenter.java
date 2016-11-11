package com.nanke.cook.ui.main;

import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.nanke.cook.R;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.source.WeatherDataRepository;
import com.nanke.cook.ui.weather.fragment.SublimePickerFragment;
import com.nanke.cook.view.swipefling.SwipeFlingAdapterView;

/**
 * Created by vince on 16/11/11.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private WeatherDataRepository weatherDataRepository;

    public MainPresenter(MainContract.View view) {
        this.weatherDataRepository = new WeatherDataRepository();
        this.view = view;
        this.view.initViewPager();
    }

    @Override
    public NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn_calendar:
                        view.showCalendar();
                        break;
                    case R.id.btn_collect_center:
                        view.startFoodsCollectedActivity();
                        break;
                    case R.id.btn_theme:
                        view.showThemeChoose();
                        break;
                    case R.id.btn_about:
                        view.startAboutActivity();
                        break;
                }
                return true;
            }
        };
    }


    @Override
    public void getWeatherOnToday(String cityname) {
        weatherDataRepository.getWeather(cityname, weatherObjCallBack);
    }

    @Override
    public AdapterView.OnItemClickListener getThemeChooseItemListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                view.saveAndNotifyThemeChange(position);
            }
        };
    }

    @Override
    public SublimePickerFragment.Callback getSublimePickerCallback() {
        return new SublimePickerFragment.Callback() {
            @Override
            public void onCancelled() {

            }

            @Override
            public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {

            }
        };
    }


    @Override
    public View.OnClickListener getWeatherClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.startWeatherActivity();
            }
        };
    }

    @Override
    public void onFBClick() {
        view.showBubblePopup();
    }


    //天气数据回调
    private ObjCallBack<Realtime> weatherObjCallBack = new ObjCallBack<Realtime>() {
        @Override
        public void onTasksLoaded(Realtime tasks) {
            view.loadWeatherOnToday(tasks);
        }

        @Override
        public void onDataNotAvailable(String msg) {
            view.onMessage(msg);
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

}
