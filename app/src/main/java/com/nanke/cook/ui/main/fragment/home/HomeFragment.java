package com.nanke.cook.ui.main.fragment.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.nanke.cook.R;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.event.BusEvent;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.collect.FoodsCollectedActivity;
import com.nanke.cook.ui.main.BackPressedInterface;
import com.nanke.cook.ui.main.MainActivity;
import com.nanke.cook.ui.main.adapter.ColorsListAdapter;
import com.nanke.cook.ui.main.adapter.MainViewPageAdapter;
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

public class HomeFragment extends BaseIconFragment implements HomeContract.View ,BackPressedInterface {

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

        mainPresenter = new HomePresenter(this);

        mainPresenter.getCategory(mActivity);
        mainPresenter.getWeatherToday("北京");
        return view;
    }

    @Override
    public void initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main_more);
        SearchManager searchManager =
                (SearchManager) mActivity.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(toolbar.getMenu().findItem(R.id.search));
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(mActivity.getComponentName()));
        searchView.setQueryHint(mActivity.getString(R.string.search_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                recyclerAdapter.getFilter().filter(s);
                return true;
            }
        });
    }

    @Override
    public void initDrawerLayout() {
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                mActivity,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.app_name,  /* "open drawer" description for accessibility */
                R.string.app_name  /* "close drawer" description for accessibility */
        );
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mActivity.setmBackPressedInterface(this);
    }

    @Override
    public void initNavigationView() {
        //获取headerview
        View headerView = navi.getHeaderView(0);
        weather_img = (ImageView) headerView.findViewById(R.id.weather_img);
        temperatureView = (TextView) headerView.findViewById(R.id.temperatureView);
        cityNameView = (TextView) headerView.findViewById(R.id.cityNameView);

        //监听各个菜单
        navi.setNavigationItemSelectedListener(mainPresenter.getNavigationItemSelectedListener());
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
    public void showThemeChooseDialog() {
        AlertDialog.Builder builder = DialogUtils.makeDialogBuilder(getActivity());
        builder.setTitle(R.string.change_theme);
        Integer[] res = new Integer[]{R.drawable.red_round, R.drawable.brown_round, R.drawable.blue_round,
                R.drawable.blue_grey_round, R.drawable.yellow_round, R.drawable.deep_purple_round,
                R.drawable.pink_round, R.drawable.green_round};
        List<Integer> list = Arrays.asList(res);
        ColorsListAdapter adapter = new ColorsListAdapter(getActivity(), list);
        adapter.setCheckItem(ThemeUtils.getCurrentTheme(getActivity()).getIntValue());
        GridView gridView = (GridView) LayoutInflater.from(getActivity()).inflate(R.layout.colors_panel_layout, null);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setCacheColorHint(0);
        gridView.setAdapter(adapter);
        builder.setView(gridView);
        final AlertDialog dialog = builder.create();
        dialog.show();
        gridView.setOnItemClickListener(mainPresenter.getThemeChooseItemListener());

    }

    @Override
    public void saveAndNotifyThemeChange(int position) {
        int value = ThemeUtils.getCurrentTheme(getActivity()).getIntValue();
        if (value != position) {
            PreferenceUtils.getInstance(getActivity()).saveParam(getString(R.string.change_theme_key), position);
        }
        EventBus.getDefault().postSticky(new BusEvent(BusEvent.TYPE_CHANGE_THEME));
    }

    @Override
    public void turnToFoodsCollected() {
        startActivity(new Intent(getActivity(), FoodsCollectedActivity.class));
    }

    @Override
    public void showCalendar() {
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(mainPresenter.getSublimePickerFragmentCallback());

        SublimeOptions options = new SublimeOptions();
        options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER);
        options.setDisplayOptions(1);

        // Enable/disable the date range selection feature
        options.setCanPickDateRange(true);
        // Valid options
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", options);
        pickerFrag.setArguments(bundle);

        pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.show(getActivity().getSupportFragmentManager(), "SUBLIME_PICKER");
    }

    @Override
    public void turnToAbout() {

    }

    @Override
    public void turnToWeather() {
        startActivity(new Intent(getActivity(), WeatherActivity.class));
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

    @Override
    public boolean onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return true;
        }
        return false;
    }

}
