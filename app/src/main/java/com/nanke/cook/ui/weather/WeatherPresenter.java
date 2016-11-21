package com.nanke.cook.ui.weather;

import android.content.Context;

import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.source.WeatherDataRepository;

import java.util.Map;

/**
 * Created by vince on 16/11/21.
 */

public class WeatherPresenter implements WeatherContract.Presenter{

    private WeatherContract.View view;
    private WeatherDataRepository weatherDataRepository;


    public WeatherPresenter(WeatherContract.View view) {
        this.view = view;
        this.weatherDataRepository = new WeatherDataRepository();
    }

    @Override
    public void getWeather(Context context, final String cityName) {
        weatherDataRepository.saveCityAndCode(context, new ObjCallBack<Map<String, String>>() {
            @Override
            public void onTasksLoaded(Map<String, String> tasks) {
                view.loadWeatherWeb("http://e.weather.com.cn/d/index/"+tasks.get(cityName)+".shtml");
            }

            @Override
            public void onDataNotAvailable(String msg) {
                view.onMessage(msg);
            }

            @Override
            public void start() {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
