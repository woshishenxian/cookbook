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
    public void getWeather(Context context) {
        weatherDataRepository.gpsLocalCity(context,cityObjCallBack);
    }
    private ObjCallBack<String> cityObjCallBack = new ObjCallBack<String>() {
        @Override
        public void onTasksLoaded(String tasks) {
            weatherDataRepository.getCodeByCityName(tasks,codeObjCallBack);
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

        }
    };


    private ObjCallBack<String> codeObjCallBack = new ObjCallBack<String>() {
        @Override
        public void onTasksLoaded(String tasks) {
            view.loadWeatherWeb("http://e.weather.com.cn/d/index/"+tasks+".shtml");
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

        }
    };
}
