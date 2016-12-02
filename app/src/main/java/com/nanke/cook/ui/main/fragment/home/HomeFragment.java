package com.nanke.cook.ui.main.fragment.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.nanke.cook.R;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.ui.about.AboutActivity;
import com.nanke.cook.ui.collect.FoodsCollectedActivity;
import com.nanke.cook.ui.main.MainActivity;
import com.nanke.cook.ui.main.adapter.MainViewPageAdapter;
import com.nanke.cook.ui.search.SearchActivity;
import com.nanke.cook.ui.weather.WeatherActivity;
import com.nanke.cook.view.NavBaseDialog;
import com.nanke.cook.view.SearchBaseDialog;
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


    private MainActivity mActivity;
    private HomePresenter presenter;

    private NavBaseDialog navBaseDialog;
    private SearchBaseDialog searchBaseDialog;


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

        presenter = new HomePresenter(this);

        presenter.getCategory(mActivity);
        return view;
    }

    @Override
    public void initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main_more);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.onMenuItemClick(item);
                return true;
            }
        });
    }


    @Override
    public void showWeatherDialog() {
        if (navBaseDialog == null) {
            navBaseDialog = new NavBaseDialog(getContext());
            navBaseDialog.setCallback(presenter.getNavCallback());
            presenter.requestLocationPermissions(getContext());
        }
        navBaseDialog.show();

    }

    @Override
    public void loadCategory(List<Category> categories) {
        viewPager.setAdapter(new MainViewPageAdapter(mActivity.getSupportFragmentManager(), categories));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void loadWeatherOnToday(Realtime realtime) {
        if (navBaseDialog != null) {
            navBaseDialog.setWeather_img(realtime.getWeather().getImgRes());
            navBaseDialog.setTemperature(realtime.getWeather().getTemperature());
            navBaseDialog.setCityName(realtime.getCity_name());
        }
    }

    @Override
    public void weatherRefreshError() {
        if (navBaseDialog != null) {
            navBaseDialog.setWeather_img(R.mipmap.d_wz);
            navBaseDialog.setTemperature("天气刷新失败");
            navBaseDialog.setCityName("");
        }
    }

    @Override
    public void refreshWeather() {
        presenter.getWeatherOnToday(getContext());
    }

    @Override
    public void showSearchDialog() {
        if (searchBaseDialog == null) {
            searchBaseDialog = new SearchBaseDialog(getContext(),presenter.getSearchCallback());
        }

        searchBaseDialog.show();
    }

    @Override
    public void startFoodsCollectedActivity() {
        startActivity(new Intent(getContext(), FoodsCollectedActivity.class));
    }

    @Override
    public void startAboutActivity() {
        startActivity(new Intent(getContext(), AboutActivity.class));
    }

    @Override
    public void startWeatherActivity() {
        startActivity(new Intent(getContext(), WeatherActivity.class));
    }

    @Override
    public void startSearchActivity(String query) {
//        mActivity.toast(query);
        Intent intent = new Intent(getContext(), SearchActivity.class);
        intent.putExtra("query",query);
        startActivity(intent);
    }

    @Override
    public String getTitle() {
        return "食谱";
    }

    @Override
    public int getIconId() {
        return 0;
    }


}
