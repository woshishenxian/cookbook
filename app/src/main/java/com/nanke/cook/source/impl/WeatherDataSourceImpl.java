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

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.nanke.cook.api.RetrofitManager;
import com.nanke.cook.api.WeatherService;
import com.nanke.cook.db.DBManager;
import com.nanke.cook.entity.City;
import com.nanke.cook.entity.CityDao;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vince on 16/11/3.
 */

public class WeatherDataSourceImpl implements WeatherDataSource {

    private static final String KEY = "82c6df79580345de2587fd00e9d4c1b7";
    private static final String TAG = "city_location";


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
    public void saveCityAndCode(final Context context, final ObjCallBack<List<City>> callBack) {
        callBack.start();
        List<City> cities = DBManager.getInstance().getDaoSession().getCityDao().queryBuilder().list();
        LogUtils.i("count", cities + "");
        if (cities.size() > 0) {
            callBack.onTasksLoaded(cities);
            callBack.onComplete();
            return;
        }

        new AsyncTask<String, Integer, List<City>>() {
            @Override
            protected List<City> doInBackground(String[] params) {

                try {
                    List<City> cities = new ArrayList<City>();
                    InputStream inputStream = context.getAssets().open("city.txt");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    String strLine = null;
                    while ((strLine = reader.readLine()) != null) {
                        LogUtils.i("city", strLine);
                        String[] splits = strLine.split("=");
                        City city = new City();
                        city.setCode(splits[0]);
                        city.setName(splits[splits.length - 1]);
                        cities.add(city);
                    }
                    reader.close();
                    inputStream.close();
                    return cities;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<City> cities) {
                if (cities != null) {
                    DBManager.getInstance().getDaoSession().getCityDao().insertInTx(cities);
                    callBack.onTasksLoaded(cities);
                    callBack.onComplete();
                } else {
                    callBack.onDataNotAvailable("解析错误");
                    callBack.onComplete();
                }
            }
        }.execute();

    }


    @Override
    public void gpsLocalCity(Context context, ObjCallBack<String> callBack) {
        callBack.start();
        AMapLocationClient mlocationClient = new AMapLocationClient(context);
        //初始化定位参数
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置返回地址信息，默认为true
        mLocationOption.setNeedAddress(true);
        //设置定位监听
        mlocationClient.setLocationListener(new CAMapLocationListener(callBack));
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setOnceLocation(true);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }


    @Override
    public void getCodeByCityName(String cityName, ObjCallBack<String> callBack) {
        String cityname = "北京";
        if(cityName.endsWith("市") || cityName.endsWith("省")){
            cityname = cityName.substring(0,cityName.length()-1);
        }
        LogUtils.i("localCity",cityname);
        City city = DBManager.getInstance().getDaoSession().getCityDao().queryBuilder().where(CityDao.Properties.Name.eq(cityname)).unique();
        if (city !=null){
            callBack.onTasksLoaded(city.getCode());
        }else {
            callBack.onDataNotAvailable("定位失败");
        }

    }

    private class CAMapLocationListener implements AMapLocationListener {
        ObjCallBack<String> callBack;

        public CAMapLocationListener(ObjCallBack<String> callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
//                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                    amapLocation.getLatitude();//获取纬度
//                    amapLocation.getLongitude();//获取经度
//                    amapLocation.getAccuracy();//获取精度信息
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date date = new Date(amapLocation.getTime());
//                    df.format(date);//定位时间
//                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                    amapLocation.getCountry();//国家信息
//                    amapLocation.getProvince();//省信息
                    if(callBack !=null){
                        //城市信息
                        callBack.onTasksLoaded(amapLocation.getCity());
                    }

//                    amapLocation.getDistrict();//城区信息
//                    amapLocation.getStreet();//街道信息
//                    amapLocation.getStreetNum();//街道门牌号信息
//                    amapLocation.getCityCode();//城市编码
//                    amapLocation.getAdCode();//地区编码
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    LogUtils.e("AmapError", "city_location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                    callBack.onDataNotAvailable(amapLocation.getErrorCode()+"errInfo:"+amapLocation.getErrorInfo());
                }
            }
            callBack.onComplete();
        }
    };


    @Override
    public boolean haveSavedForCity() {
        return DBManager.getInstance().getDaoSession().getCityDao().queryBuilder().count() >0;
    }
}
