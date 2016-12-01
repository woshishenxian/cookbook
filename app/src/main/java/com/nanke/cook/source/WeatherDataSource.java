package com.nanke.cook.source;

import android.content.Context;

import com.nanke.cook.entity.City;
import com.nanke.cook.entity.weather.Data;
import com.nanke.cook.entity.weather.Life;
import com.nanke.cook.entity.weather.Pm25;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.entity.weather.Weather;
import com.nanke.cook.entity.weather.WeatherData;

import java.util.List;
import java.util.Map;

/**
 * Created by vince on 16/11/3.
 */

public interface WeatherDataSource {

    /**
     * 获取天气
     * @param cityname
     * @param callBack
     */
    public void getWeatherAll(String cityname, ObjCallBack<Data> callBack);


    /**
     * 获取当天天气
     * @param cityname
     * @param callBack
     */
    public void getWeather(String cityname, ObjCallBack<Realtime> callBack);

    /**
     * 获取未来5天的天气
     * @param cityname
     * @param callBack
     */
    public void getFutureWeather(String cityname, ArrCallBack<Weather> callBack);

    /**
     * 获取pm2.5
     * @param cityname
     * @param callBack
     */
    public void getPm25(String cityname, ObjCallBack<Pm25> callBack);

    /**
     * 获取生活了指数
     * @param cityname
     * @param callBack
     */
    public void getLife(String cityname, ObjCallBack<Life> callBack);

    /**
     * 判读是否已经保存
     */
    public boolean haveSavedForCity();

    /**
     * 城市列表
     * @param context
     * @param callBack
     */
    public void saveCityAndCode(Context context, ObjCallBack<List<City>> callBack);
    /**
     * 定位本地城市
     * @param context
     * @param callBack
     */
    public void gpsLocalCity(Context context, ObjCallBack<String> callBack);

    /**
     * 获取城市编号
     * @param cityName
     * @param callBack
     */
    public void getCodeByCityName(String cityName, ObjCallBack<String> callBack);
}
