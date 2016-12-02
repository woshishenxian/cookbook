package com.nanke.cook.ui.main.fragment.home;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.nanke.cook.R;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.source.ArrCallBack;
import com.nanke.cook.source.FoodsDataRepository;
import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.source.WeatherDataRepository;
import com.nanke.cook.utils.PermissionM;
import com.nanke.cook.view.NavBaseDialog;
import com.nanke.cook.view.SearchBaseDialog;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class HomePresenter implements HomeContract.Presenter {

    /**
     * 需要进行检测的权限数组
     */
    private String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private int requestCode = 0;

    private WeatherDataRepository weatherDataRepository;
    private PermissionM manager;

    FoodsDataRepository foodsDataRepository;
    HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.foodsDataRepository = new FoodsDataRepository();
        this.view = view;
        this.view.initToolbar();
        this.weatherDataRepository = new WeatherDataRepository();
        this.manager = new PermissionM(permissionProxy);
    }

    @Override
    public void getCategory(Context cotnext) {
        foodsDataRepository.getCategory(cotnext, categoryArrCallBack);
    }


    @Override
    public void onMenuItemClick(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.btn_collect_center:
                view.startFoodsCollectedActivity();
                break;
            case R.id.btn_about:
                view.startAboutActivity();
                break;
            case R.id.btn_weather:
                view.showWeatherDialog();
                break;
            case R.id.search:
                view.showSearchDialog();
                break;
        }
    }




    @Override
    public View.OnClickListener getWeatherRefreshListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.refreshWeather();
            }
        };
    }


    @Override
    public View.OnClickListener getWeatherClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.startWeatherActivity();
            }
        };
    }


    @Override
    public void getWeatherOnToday(Context context) {
        weatherDataRepository.gpsLocalCity(context, cityObjCallBack);
    }

    @Override
    public void onRequestPermissionsResult(Context context, int requestCode, int[] paramArrayOfInt) {
        manager.onRequestPermissionsResult(context, requestCode, paramArrayOfInt);
    }

    @Override
    public void requestLocationPermissions(Context context) {
        manager.requestPermissions(context,requestCode,needPermissions);
    }

    @Override
    public NavBaseDialog.Callback getNavCallback() {
        return new NavBaseDialog.Callback() {
            @Override
            public void contentClick() {
                view.refreshWeather();
            }

            @Override
            public void rightClick() {
                view.startWeatherActivity();
            }
        };
    }

    @Override
    public SearchBaseDialog.Callback getSearchCallback() {
        return new SearchBaseDialog.Callback(){
            @Override
            public void query(String string) {
                if(!TextUtils.isEmpty(string)){
                    view.startSearchActivity(string);
                }

            }
        };
    }

    //分类数据回调
    private ArrCallBack<Category> categoryArrCallBack = new ArrCallBack<Category>() {
        @Override
        public void onTasksLoaded(List<Category> tasks) {
            view.loadCategory(tasks);
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

    //定位城市回调
    private ObjCallBack<String> cityObjCallBack = new ObjCallBack<String>() {
        @Override
        public void onTasksLoaded(String tasks) {
            weatherDataRepository.getWeather(tasks, weatherObjCallBack);
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


    //天气数据回调
    private ObjCallBack<Realtime> weatherObjCallBack = new ObjCallBack<Realtime>() {
        @Override
        public void onTasksLoaded(Realtime tasks) {
            view.loadWeatherOnToday(tasks);
        }

        @Override
        public void onDataNotAvailable(String msg) {
            view.weatherRefreshError();
        }

        @Override
        public void start() {
        }

        @Override
        public void onComplete() {

        }
    };


    private PermissionM.PermissionProxy<Activity> permissionProxy = new PermissionM.PermissionProxy<Activity>() {
        @Override
        public void grant(Activity source, int requestCode) {
            view.refreshWeather();
        }

        @Override
        public void denied(final Activity source, int requestCode) {
            String[] btns = {"取消", "设置"};
            MaterialDialog dialog = new MaterialDialog(source);
            dialog.title("提示")
                    .content("当前应用缺少必要权限。\n请点击\"设置\"-\"权限\"-打开所需权限。")
                    .btnText(btns).widthScale(0.7f).show();

            dialog.setOnBtnClickL(null, new OnBtnClickL() {
                @Override
                public void onBtnClick() {
                    startAppSettings(source);
                }
            });
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    view.refreshWeather();
                }
            });
        }

        @Override
        public boolean needShowRationale(int requestCode) {
            return true;
        }
    };

    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings(Context context) {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

}
