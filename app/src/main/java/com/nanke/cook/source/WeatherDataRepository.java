package com.nanke.cook.source;

import com.nanke.cook.entity.weather.Life;
import com.nanke.cook.entity.weather.Pm25;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.entity.weather.Weather;
import com.nanke.cook.entity.weather.WeatherData;
import com.nanke.cook.source.impl.WeatherDataSourceImpl;

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
}
