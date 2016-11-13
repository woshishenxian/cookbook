package com.nanke.cook.ui.weather;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.entity.weather.Data;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.weather.adapter.RecyclerViewAdapter;
import com.qiushui.blurredview.BlurredView;

import butterknife.InjectView;

/**
 * Created by vince on 16/11/7.
 */

public class WeatherActivity extends BaseActivity<WeatherPresenter> implements WeatherContract.View {


    @InjectView(R.id.blurredView)
    BlurredView blurredView;
    @InjectView(R.id.city)
    TextView city;
    @InjectView(R.id.update_time)
    TextView updateTime;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycleView)
    RecyclerView recycleView;



    private int mScrollerY;

    private int mAlpha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.getWeahter("北京");
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_weather;
    }

    @Override
    public WeatherPresenter initPresenter() {
        return new WeatherPresenter(this);
    }

    @Override
    public void initToolbar() {

    }


    @Override
    public void loadWeahter(Data data) {
        Realtime realtime = data.getRealtime();
        city.setText(realtime.getCity_name());
        updateTime.setText("更新时间："+realtime.getTime());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);

        recycleView.setAdapter(new RecyclerViewAdapter(this,data));

        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollerY += dy;
                if (Math.abs(mScrollerY) > 1000) {
                    blurredView.setBlurredTop(100);
                    mAlpha = 100;
                } else {
                    blurredView.setBlurredTop(mScrollerY / 10);
                    mAlpha = Math.abs(mScrollerY) / 10;
                }
                blurredView.setBlurredLevel(mAlpha);
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void onMessage(String msg) {
        this.toast(msg);
    }

}
