package com.nanke.cook.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.dialog.widget.popup.BubblePopup;
import com.nanke.cook.R;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.about.AboutActivity;
import com.nanke.cook.ui.collect.FoodsCollectedActivity;
import com.nanke.cook.ui.main.fragment.daily.DailyFragment;
import com.nanke.cook.ui.main.fragment.home.HomeFragment;
import com.nanke.cook.ui.weather.WeatherActivity;
import com.nanke.cook.view.indicator.BaseIconFragment;
import com.nanke.cook.view.indicator.BaseViewPager;
import com.nanke.cook.view.indicator.IconTabPageIndicator;
import com.nanke.cook.view.indicator.TabPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @InjectView(R.id.indicator)
    IconTabPageIndicator indicator;
    @InjectView(R.id.viewpager)
    BaseViewPager viewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void initViewPager() {
        viewPager.setNoScroll(true);

        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(),
                initFragmentList()));
        viewPager.setOffscreenPageLimit(0);
        indicator.setViewPager(viewPager);
    }



    @Override
    public void startAboutActivity() {
        startActivity(new Intent(this, AboutActivity.class));
    }


    @OnClick(R.id.btn_fb)
    public void onFBClick() {
        presenter.onFBClick();
    }


    private List<BaseIconFragment> initFragmentList() {
        List<BaseIconFragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new DailyFragment());
        return fragments;
    }

}
