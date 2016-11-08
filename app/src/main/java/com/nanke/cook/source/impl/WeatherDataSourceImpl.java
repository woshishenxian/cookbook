package com.nanke.cook.source.impl;

import com.nanke.cook.api.RetrofitManager;
import com.nanke.cook.api.WeatherService;
import com.nanke.cook.entity.weather.Data;
import com.nanke.cook.entity.weather.Life;
import com.nanke.cook.entity.weather.Pm25;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.entity.weather.Weather;
import com.nanke.cook.entity.weather.WeatherData;
import com.nanke.cook.source.ArrCallBack;
import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.source.WeatherDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vince on 16/11/3.
 */

public class WeatherDataSourceImpl implements WeatherDataSource {

    private static final String KEY = "82c6df79580345de2587fd00e9d4c1b7";

    private WeatherService weatherService;

    public WeatherDataSourceImpl() {
        this.weatherService = RetrofitManager.getInstance().create(WeatherService.class);
    }

    @Override
    public void getWeather(String cityname,final ObjCallBack<Realtime> callBack) {
        callBack.start();
        weatherService.getWeatherByCityName(cityname,KEY).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if(response.isSuccessful()){
                    WeatherData weatherData = response.body();
                    //对数据的处理操作
                    if(weatherData.getError_code() == 0){
                        callBack.onTasksLoaded(weatherData.getResult().getData().getRealtime());
                    }else{
                        callBack.onDataNotAvailable(weatherData.getReason());
                    }

                }else{
                    //请求出现错误例如：404 或者 500
                    callBack.onDataNotAvailable("网络异常");
                }
                callBack.onComplete();
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                callBack.onComplete();
                callBack.onDataNotAvailable(t.getMessage());
            }
        });
    }

    @Override
    public void getFutureWeather(String cityname,final ArrCallBack<Weather> callBack) {
        callBack.start();
        weatherService.getWeatherByCityName(cityname,KEY).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if(response.isSuccessful()){
                    WeatherData weatherData = response.body();
                    //对数据的处理操作
                    if(weatherData.getError_code() == 0){
                        callBack.onTasksLoaded(weatherData.getResult().getData().getWeather());
                    }else{
                        callBack.onDataNotAvailable(weatherData.getReason());
                    }

                }else{
                    //请求出现错误例如：404 或者 500
                    callBack.onDataNotAvailable("网络异常");
                }
                callBack.onComplete();
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                callBack.onComplete();
                callBack.onDataNotAvailable(t.getMessage());
            }
        });
    }

    @Override
    public void getPm25(String cityname,final ObjCallBack<Pm25> callBack) {
        callBack.start();
        weatherService.getWeatherByCityName(cityname,KEY).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if(response.isSuccessful()){
                    WeatherData weatherData = response.body();
                    //对数据的处理操作
                    if(weatherData.getError_code() == 0){
                        callBack.onTasksLoaded(weatherData.getResult().getData().getPm25());
                    }else{
                        callBack.onDataNotAvailable(weatherData.getReason());
                    }
                }else{
                    //请求出现错误例如：404 或者 500
                    callBack.onDataNotAvailable("网络异常");
                }
                callBack.onComplete();
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                callBack.onComplete();
                callBack.onDataNotAvailable(t.getMessage());
            }
        });
    }

    @Override
    public void getLife(String cityname,final ObjCallBack<Life> callBack) {
        callBack.start();
        weatherService.getWeatherByCityName(cityname,KEY).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if(response.isSuccessful()){
                    WeatherData weatherData = response.body();
                    //对数据的处理操作
                    if(weatherData.getError_code() == 0){
                        callBack.onTasksLoaded(weatherData.getResult().getData().getLife());
                    }else{
                        callBack.onDataNotAvailable(weatherData.getReason());
                    }
                }else{
                    //请求出现错误例如：404 或者 500
                    callBack.onDataNotAvailable("网络异常");
                }
                callBack.onComplete();
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                callBack.onComplete();
                callBack.onDataNotAvailable(t.getMessage());
            }
        });
    }

    @Override
    public void getWeatherAll(String cityname,final ObjCallBack<Data> callBack) {
        callBack.start();
        weatherService.getWeatherByCityName(cityname,KEY).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if(response.isSuccessful()){
                    WeatherData weatherData = response.body();
                    //对数据的处理操作
                    if(weatherData.getError_code() == 0){
                        callBack.onTasksLoaded(weatherData.getResult().getData());
                    }else{
                        callBack.onDataNotAvailable(weatherData.getReason());
                    }
                }else{
                    //请求出现错误例如：404 或者 500
                    callBack.onDataNotAvailable("网络异常");
                }
                callBack.onComplete();
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                callBack.onComplete();
                callBack.onDataNotAvailable(t.getMessage());
            }
        });
    }
}
