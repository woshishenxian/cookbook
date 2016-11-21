package com.nanke.cook.source;

import android.content.Context;

import com.nanke.cook.entity.weather.Data;
import com.nanke.cook.entity.weather.Life;
import com.nanke.cook.entity.weather.Pm25;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.entity.weather.Weather;
import com.nanke.cook.entity.weather.WeatherData;
import com.nanke.cook.source.impl.WeatherDataSourceImpl;

import java.util.Map;

/**
 * Created by vince on 16/11/3.
 */

public class WeatherDataRepository implements WeatherDataSource{

    private WeatherDataSource weatherDataSource;

    public WeatherDataRepository() {
        weatherDataSource = new WeatherDataSourceImpl();
    }

    @Override
    public void getWeather(String cityname, ObjCallBack<Realtime> callBack) {
        weatherDataSource.getWeather(cityname, callBack);
    }

    @Override
    public void getFutureWeather(String cityname, ArrCallBack<Weather> callBack) {
        weatherDataSource.getFutureWeather(cityname, callBack);
    }

    @Override
    public void getPm25(String cityname, ObjCallBack<Pm25> callBack) {
        weatherDataSource.getPm25(cityname, callBack);
    }

    @Override
    public void getLife(String cityname, ObjCallBack<Life> callBack) {
        weatherDataSource.getLife(cityname, callBack);
    }

    @Override
    public void getWeatherAll(String cityname, ObjCallBack<Data> callBack) {
        weatherDataSource.getWeatherAll(cityname,callBack);
    }

    @Override
    public void saveCityAndCode(Context context, ObjCallBack<Map<String, String>> callBack) {
        weatherDataSource.saveCityAndCode(context,callBack);
    }
}
