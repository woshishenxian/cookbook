package com.nanke.cook.ui.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.entity.weather.Data;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.weather.adapter.FutureAdapter;
import com.nanke.cook.view.AutoSwipeRefreshLayout;
import com.nanke.cook.view.FixedListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vince on 16/11/7.
 */

public class WeatherActivity extends BaseActivity implements WeatherContract.View, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.swipeRefreshLayout)
    AutoSwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.cityname)
    TextView cityname;
    @InjectView(R.id.time)
    TextView time;
    @InjectView(R.id.wind)
    TextView wind;
    @InjectView(R.id.humidity)
    TextView humidity;
    @InjectView(R.id.temperature)
    TextView temperature;
    @InjectView(R.id.weather_img)
    TextView weather_img;
    @InjectView(R.id.fixedListView)
    FixedListView fixedListView;


    private WeatherPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ButterKnife.inject(this);


        presenter = new WeatherPresenter(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        initToolbar();
        onRefresh();
    }


    private void initToolbar() {

        toolbar.inflateMenu(R.menu.menu_daily_more);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.onMenuItemClick(WeatherActivity.this, item.getItemId());
                return true;
            }
        });
    }


    @Override
    public void onRefresh() {
        presenter.getWeahter("北京");
    }

    @Override
    public void loadWeahter(Data data) {
        Realtime realtime = data.getRealtime();
        time.setText("更新时间: " + realtime.getTime());
        cityname.setText(realtime.getCity_name());
        wind.setText("风力\n" + realtime.getWind().getDirect() + realtime.getWind().getPower());
        temperature.setText(realtime.getWeather().getTemperature());
        humidity.setText(realtime.getWeather().getHumidity());

        weather_img.setText(realtime.getWeather().getInfo());

        fixedListView.setAdapter(new FutureAdapter(this, data.getWeather()));

    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.startRefreshing();
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.stopRefreshing();
    }

    @Override
    public void onError(String msg) {
        this.toast(msg);
    }

}
