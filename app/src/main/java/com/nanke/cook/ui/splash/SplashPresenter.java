package com.nanke.cook.ui.splash;

import android.content.Context;
import android.view.animation.Animation;

import com.nanke.cook.entity.City;
import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.source.WeatherDataRepository;
import com.nanke.cook.utils.LogUtils;
import com.nanke.cook.utils.PermissionsCheckManager;

import java.util.List;

/**
 * Created by vince on 16/11/22.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;
    private WeatherDataRepository weatherDataRepository;
    private PermissionsCheckManager manager;



    public SplashPresenter(SplashContract.View view) {
        this.view = view;
        this.weatherDataRepository = new WeatherDataRepository();
        this.manager = new PermissionsCheckManager();
    }

    @Override
    public void saveCityAndCode(Context context) {
        weatherDataRepository.saveCityAndCode(context,objCallBack);
    }

    @Override
    public void onRequestPermissionsResult(Context context,int requestCode, int[] paramArrayOfInt) {
        manager.onRequestPermissionsResult(context,requestCode, paramArrayOfInt);
    }


    @Override
    public void onResume(Context context) {
        manager.checkPermissions(context);
    }

    @Override
    public Animation.AnimationListener getBackgroundAnimationListener() {
        return new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {
                view.backgroundAnimStart();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.backgroundAnimEnd();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }



    @Override
    public Animation.AnimationListener getTitleAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.titleAnimEnd();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    private ObjCallBack<List<City>> objCallBack = new ObjCallBack<List<City>>() {
        @Override
        public void onTasksLoaded(List<City> tasks) {
            if(LogUtils.debug)
                view.saveSuccess();
        }

        @Override
        public void onDataNotAvailable(String msg) {
            if(LogUtils.debug)
            view.saveFailed(msg);
        }

        @Override
        public void start() {

        }

        @Override
        public void onComplete() {
            view.saveSuccess();
        }
    };
}
