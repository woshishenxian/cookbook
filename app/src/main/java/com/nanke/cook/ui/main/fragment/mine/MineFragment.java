package com.nanke.cook.ui.main.fragment.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.entity.weather.WeatherData;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.view.AutoSwipeRefreshLayout;
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

    @InjectView(R.id.swipeRefreshLayout)
    AutoSwipeRefreshLayout swipeRefreshLayout;

    @InjectView(R.id.temperature)
    TextView temperature;
    @InjectView(R.id.date)
    TextView date;
    @InjectView(R.id.cityname)
    TextView cityname;
    @InjectView(R.id.wind)
    TextView wind;


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

        onRefresh();
        return view;
    }

    @OnClick(R.id.btn_collect)
    public void onCollectBtnClick() {
        presenter.onCollectBtnClick(activity);
    }

    @OnClick(R.id.btn_calendar)
    public void onCalendarBtnClick() {
        presenter.onCalendarBtnClick(activity);
    }

    @Override
    public void onRefresh() {
        presenter.getWeahter("北京");
    }

    @Override
    public void loadWeahter(Realtime realtime) {
        temperature.setText(realtime.getWeather().getTemperature() +"°C");
        date.setText(realtime.getDate());
        cityname.setText(realtime.getCity_name());
        wind.setText(realtime.getWind().getDirect() + " " + realtime.getWind().getPower());
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
        return "我的";
    }

    @Override
    public int getIconId() {
        return R.drawable.tab_mine_selector;
    }
}
