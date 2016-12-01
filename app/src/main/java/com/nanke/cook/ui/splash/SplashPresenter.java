package com.nanke.cook.ui.splash;

import android.content.Context;
import android.os.Handler;

import com.nanke.cook.entity.City;
import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.source.WeatherDataRepository;

import java.util.List;

/**
 * Created by vince on 16/11/22.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;
    private WeatherDataRepository weatherDataRepository;
    private Handler handler = new Handler();



    public SplashPresenter(SplashContract.View view) {
        this.view = view;
        this.weatherDataRepository = new WeatherDataRepository();
    }

    @Override
    public void initCityData(Context context) {
        if(weatherDataRepository.haveSavedForCity()){
            turnTo();
        }else{
            weatherDataRepository.saveCityAndCode(context,objCallBack);
        }

    }

    private ObjCallBack<List<City>> objCallBack = new ObjCallBack<List<City>>() {
        @Override
        public void onTasksLoaded(List<City> tasks) {
            turnTo();
        }

        @Override
        public void onDataNotAvailable(String msg) {

        }

        @Override
        public void start() {

        }

        @Override
        public void onComplete() {
        }
    };


    private void turnTo(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.startMainActivity();
            }
        },1500);
    }

}
