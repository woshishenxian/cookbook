package com.nanke.cook.source;

import com.nanke.cook.entity.weather.Life;
import com.nanke.cook.entity.weather.Pm25;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.entity.weather.Weather;
import com.nanke.cook.entity.weather.WeatherData;

/**
 * Created by vince on 16/11/3.
 */

public interface WeatherDataSource {


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
}
