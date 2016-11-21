package com.nanke.cook.source.impl;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import com.nanke.cook.api.RetrofitManager;
import com.nanke.cook.api.WeatherService;
import com.nanke.cook.db.DBManager;
import com.nanke.cook.entity.weather.Data;
import com.nanke.cook.entity.weather.Life;
import com.nanke.cook.entity.weather.Pm25;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.entity.weather.Weather;
import com.nanke.cook.entity.weather.WeatherData;
import com.nanke.cook.source.ArrCallBack;
import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.source.WeatherDataSource;
import com.nanke.cook.utils.LogUtils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vince on 16/11/3.
 */

public class WeatherDataSourceImpl implements WeatherDataSource {

    private static final String KEY = "82c6df79580345de2587fd00e9d4c1b7";
    private static final String TAG = "location";


    private WeatherService weatherService;

    public WeatherDataSourceImpl() {
        this.weatherService = RetrofitManager.getInstance().create(WeatherService.class);
    }

    @Override
    public void getWeather(String cityname, final ObjCallBack<Realtime> callBack) {
        callBack.start();
        weatherService.getWeatherByCityName(cityname, KEY).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    WeatherData weatherData = response.body();
                    //对数据的处理操作
                    if (weatherData.getError_code() == 0) {
                        callBack.onTasksLoaded(weatherData.getResult().getData().getRealtime());
                    } else {
                        callBack.onDataNotAvailable(weatherData.getReason());
                    }

                } else {
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
    public void getFutureWeather(String cityname, final ArrCallBack<Weather> callBack) {
        callBack.start();
        weatherService.getWeatherByCityName(cityname, KEY).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    WeatherData weatherData = response.body();
                    //对数据的处理操作
                    if (weatherData.getError_code() == 0) {
                        callBack.onTasksLoaded(weatherData.getResult().getData().getWeather());
                    } else {
                        callBack.onDataNotAvailable(weatherData.getReason());
                    }

                } else {
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
    public void getPm25(String cityname, final ObjCallBack<Pm25> callBack) {
        callBack.start();
        weatherService.getWeatherByCityName(cityname, KEY).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    WeatherData weatherData = response.body();
                    //对数据的处理操作
                    if (weatherData.getError_code() == 0) {
                        callBack.onTasksLoaded(weatherData.getResult().getData().getPm25());
                    } else {
                        callBack.onDataNotAvailable(weatherData.getReason());
                    }
                } else {
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
    public void getLife(String cityname, final ObjCallBack<Life> callBack) {
        callBack.start();
        weatherService.getWeatherByCityName(cityname, KEY).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    WeatherData weatherData = response.body();
                    //对数据的处理操作
                    if (weatherData.getError_code() == 0) {
                        callBack.onTasksLoaded(weatherData.getResult().getData().getLife());
                    } else {
                        callBack.onDataNotAvailable(weatherData.getReason());
                    }
                } else {
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
    public void getWeatherAll(String cityname, final ObjCallBack<Data> callBack) {
        callBack.start();
        weatherService.getWeatherByCityName(cityname, KEY).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    WeatherData weatherData = response.body();
                    //对数据的处理操作
                    if (weatherData.getError_code() == 0) {
                        callBack.onTasksLoaded(weatherData.getResult().getData());
                    } else {
                        callBack.onDataNotAvailable(weatherData.getReason());
                    }
                } else {
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
    public void saveCityAndCode(final Context context, final ObjCallBack<Map<String, String>> callBack) {
        new AsyncTask<String, Integer, Map<String, String>>() {
            @Override
            protected Map<String, String> doInBackground(String[] params) {
                Map<String, String> map = new HashMap<String, String>();
                try {
                    InputStream inputStream = context.getAssets().open("city.txt");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    String strLine = null;
                    while ((strLine = reader.readLine()) != null) {
                        LogUtils.i("city", strLine);
                        String[] splits = strLine.split("=");
                        map.put(splits[splits.length - 1], splits[0]);
                    }
                    reader.close();
                    inputStream.close();

                } catch (Exception e) {
                    callBack.onDataNotAvailable("解析错误");
                }
                return map;
            }

            @Override
            protected void onPostExecute(Map<String, String> o) {
                super.onPostExecute(o);
                if (o.size() > 0) {
                    callBack.onTasksLoaded(o);
                }
            }
        }.execute();

    }

    @Override
    public void gpsLocalCity(Context context, ObjCallBack<String> callBack) {

    }


}
