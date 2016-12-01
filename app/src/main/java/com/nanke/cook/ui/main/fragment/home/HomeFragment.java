package com.nanke.cook.ui.main.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.dialog.widget.popup.BubblePopup;
import com.nanke.cook.R;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.entity.weather.Weather;
import com.nanke.cook.ui.about.AboutActivity;
import com.nanke.cook.ui.collect.FoodsCollectedActivity;
import com.nanke.cook.ui.main.MainActivity;
import com.nanke.cook.ui.main.adapter.MainViewPageAdapter;
import com.nanke.cook.ui.search.SearchResultsActivity;
import com.nanke.cook.ui.weather.WeatherActivity;
import com.nanke.cook.view.NavBaseDialog;
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

//        SearchManager searchManager =
//                (SearchManager) mActivity.getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(toolbar.getMenu().findItem(R.id.search));
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(mActivity.getComponentName()));
//        searchView.setQueryHint(mActivity.getString(R.string.search_hint));
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
////                recyclerAdapter.getFilter().filter(s);
//                return true;
//            }
//        });
    }


    @Override
    public void showWeatherDialog() {
        if(navBaseDialog == null){
            navBaseDialog = new NavBaseDialog(getContext());
            navBaseDialog.setCallback(presenter.getCallback());
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
//        if(navBaseDialog !=null){
//            navBaseDialog.setWeather_img(R.mipmap.d_wz);
//            navBaseDialog.setTemperature("更新中...");
//            navBaseDialog.setCityName("");
//        }
        presenter.getWeatherOnToday(getContext());
    }

    @Override
    public void startSearchActivity() {
        startActivity(new Intent(getContext(), SearchResultsActivity.class));
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
