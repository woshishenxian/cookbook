package com.nanke.cook.ui.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.nanke.cook.R;
import com.nanke.cook.entity.weather.Data;
import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.source.WeatherDataRepository;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.weather.fragment.SublimePickerFragment;

/**
 * Created by vince on 16/11/3.
 */

public class WeatherPresenter implements WeatherContract.Presenter {

    private WeatherContract.View view;
    private WeatherDataRepository weatherDataRepository;

    public WeatherPresenter(WeatherContract.View view) {
        this.view= view;
        weatherDataRepository = new WeatherDataRepository();

    }

    @Override
    public void getWeahter(String cityname) {
        weatherDataRepository.getWeatherAll(cityname,weatherCallBack);
    }

    private ObjCallBack weatherCallBack = new  ObjCallBack<Data>(){
        @Override
        public void onTasksLoaded(Data tasks) {
            view.loadWeahter(tasks);
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



    @Override
    public void onMenuItemClick(Context context, int itemId) {

    }


    private void onCalendarBtnClick(BaseActivity activity) {
        if(!(activity instanceof BaseActivity)){
            return;
        }

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




    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {

        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {

        }
    };
}
