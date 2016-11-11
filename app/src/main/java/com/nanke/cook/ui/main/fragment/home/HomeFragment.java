package com.nanke.cook.ui.main.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.nanke.cook.R;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.event.BusEvent;
import com.nanke.cook.ui.about.AboutActivity;
import com.nanke.cook.ui.collect.FoodsCollectedActivity;
import com.nanke.cook.ui.main.MainActivity;
import com.nanke.cook.ui.main.adapter.ColorsListAdapter;
import com.nanke.cook.ui.main.adapter.MainViewPageAdapter;
import com.nanke.cook.ui.search.SearchResultsActivity;
import com.nanke.cook.ui.weather.WeatherActivity;
import com.nanke.cook.ui.weather.fragment.SublimePickerFragment;
import com.nanke.cook.utils.DialogUtils;
import com.nanke.cook.utils.PreferenceUtils;
import com.nanke.cook.utils.ThemeUtils;
import com.nanke.cook.view.indicator.BaseIconFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vince on 16/10/31.
 */

public class HomeFragment extends BaseIconFragment implements HomeContract.View  {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;


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

        mainPresenter = new HomePresenter(this);

        mainPresenter.getCategory(mActivity);
        return view;
    }

    @Override
    public void initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main_more);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
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
    public void loadCategory(List<Category> categories) {
        viewPager.setAdapter(new MainViewPageAdapter(mActivity.getSupportFragmentManager(), categories));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void turnToSearch() {
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
