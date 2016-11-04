package com.nanke.cook.ui.main.fragment.mine;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.entity.weather.Data;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.entity.weather.WeatherData;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.main.adapter.FutureAdapter;
import com.nanke.cook.view.AutoSwipeRefreshLayout;
import com.nanke.cook.view.FixedListView;
import com.nanke.cook.view.indicator.BaseIconFragment;

import org.w3c.dom.Text;

import java.net.URLEncoder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by vince on 16/10/31.
 */

public class MineFragment extends BaseIconFragment implements MineContract.View, SwipeRefreshLayout.OnRefreshListener {



    private MinePresenter presenter;
    private BaseActivity activity;

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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);

        ButterKnife.inject(this, view);

        presenter = new MinePresenter(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        initToolbar();
        onRefresh();
        return view;
    }

    private void initToolbar(){
        toolbar.inflateMenu(R.menu.menu_weather_more);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.onMenuItemClick(activity,item.getItemId());
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
        time.setText("更新时间: "+realtime.getTime());
        cityname.setText(realtime.getCity_name());
        wind.setText("风力\n"+realtime.getWind().getDirect()  + realtime.getWind().getPower());
        temperature.setText(realtime.getWeather().getTemperature());
        humidity.setText(realtime.getWeather().getHumidity());

        weather_img.setText(realtime.getWeather().getInfo());

        fixedListView.setAdapter(new FutureAdapter(activity,data.getWeather()));

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
        activity.toast(msg);
    }

    @Override
    public String getTitle() {
        return "天气";
    }

    @Override
    public int getIconId() {
        return 0;
    }
}
