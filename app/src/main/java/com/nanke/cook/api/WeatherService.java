package com.nanke.cook.api;

import com.nanke.cook.entity.weather.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vince on 16/11/3.
 */

public interface WeatherService {

    @GET("http://op.juhe.cn/onebox/weather/query")
    Call<WeatherData> getWeatherByCityName(@Query("cityname") String cityName,@Query("key") String key);
}
