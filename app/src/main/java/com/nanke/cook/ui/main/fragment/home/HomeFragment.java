package com.nanke.cook.ui.main.fragment.home;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.nanke.cook.R;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.main.MainActivity;
import com.nanke.cook.ui.main.adapter.MainViewPageAdapter;
import com.nanke.cook.view.indicator.BaseIconFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vince on 16/10/31.
 */

public class HomeFragment extends BaseIconFragment implements HomeContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.navi)
    NavigationView navi;

    TextView cityNameView;
    TextView temperatureView;
    ImageView weather_img;

    private MainActivity mActivity;
    private HomePresenter mainPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_root, null);
        ButterKnife.inject(this, view);

        initNavigationView();

        mainPresenter = new HomePresenter(this);
        mainPresenter.initToolbar(mActivity, toolbar, mDrawerLayout);
        mainPresenter.getCategory(mActivity);
        mainPresenter.getWeatherToday("北京");
        return view;
    }


    private void initNavigationView() {
        //获取headerview
        View headerView = navi.getHeaderView(0);
        weather_img = (ImageView) headerView.findViewById(R.id.weather_img);
        temperatureView = (TextView) headerView.findViewById(R.id.temperatureView);
        cityNameView = (TextView) headerView.findViewById(R.id.cityNameView);

        //监听各个菜单
        navi.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }


    @Override
    public void loadCategory(List<Category> categories) {
        viewPager.setAdapter(new MainViewPageAdapter(mActivity.getSupportFragmentManager(), categories));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void loadWeatherToday(Realtime realtime) {

        weather_img.setImageResource(realtime.getWeather().getImgRes());
        temperatureView.setText(realtime.getWeather().getTemperature());
        cityNameView.setText(realtime.getCity_name());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String msg) {
        mActivity.toast(msg);
    }


    @Override
    public String getTitle() {
        return "食谱";
    }

    @Override
    public int getIconId() {
        return 0;
    }


    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            return mainPresenter.onNavigationItemSelectedListener(mActivity,item);
        }
    };


}
