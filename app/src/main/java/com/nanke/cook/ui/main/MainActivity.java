package com.nanke.cook.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.flyco.dialog.widget.popup.BubblePopup;
import com.nanke.cook.R;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.event.BusEvent;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.about.AboutActivity;
import com.nanke.cook.ui.collect.FoodsCollectedActivity;
import com.nanke.cook.ui.main.adapter.ColorsListAdapter;
import com.nanke.cook.ui.main.fragment.home.HomeFragment;
import com.nanke.cook.ui.main.fragment.daily.DailyFragment;
import com.nanke.cook.ui.weather.WeatherActivity;
import com.nanke.cook.ui.weather.fragment.SublimePickerFragment;
import com.nanke.cook.utils.DialogUtils;
import com.nanke.cook.utils.PreferenceUtils;
import com.nanke.cook.utils.ThemeUtils;
import com.nanke.cook.view.indicator.BaseIconFragment;
import com.nanke.cook.view.indicator.BaseViewPager;
import com.nanke.cook.view.indicator.IconTabPageIndicator;
import com.nanke.cook.view.indicator.TabPagerAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @InjectView(R.id.indicator)
    IconTabPageIndicator indicator;
    @InjectView(R.id.viewpager)
    BaseViewPager viewPager;
    @InjectView(R.id.btn_fb)
    FloatingActionButton floatingActionButton;

    TextView cityNameView;
    TextView temperatureView;
    ImageView weather_img;

    BubblePopup bubblePopup;


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
    public void showBubblePopup() {
        if (bubblePopup == null) {
            View inflate = View.inflate(this, R.layout.dialog_navigation, null);
            NavigationView navi = (NavigationView) inflate.findViewById(R.id.navi);
            View headerView = navi.getHeaderView(0);
            weather_img = (ImageView) headerView.findViewById(R.id.weather_img);
            temperatureView = (TextView) headerView.findViewById(R.id.temperatureView);
            cityNameView = (TextView) headerView.findViewById(R.id.cityNameView);

            headerView.setOnClickListener(presenter.getWeatherClickListener());
            //监听各个菜单
            navi.setNavigationItemSelectedListener(presenter.getNavigationItemSelectedListener());
            presenter.getWeatherOnToday("青岛");
            bubblePopup = new BubblePopup(this, inflate).anchorView(floatingActionButton)
                    .showAnim(null).dismissAnim(null).dimEnabled(true)
                    .cornerRadius(5f).bubbleColor(Color.WHITE);
        }
        bubblePopup.show();

    }


    @Override
    public void loadWeatherOnToday(Realtime realtime) {
        weather_img.setImageResource(realtime.getWeather().getImgRes());
        temperatureView.setText(realtime.getWeather().getTemperature());
        cityNameView.setText(realtime.getCity_name());
    }

    @Override
    public void showCalendar() {
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(presenter.getSublimePickerCallback());

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
        pickerFrag.show(getSupportFragmentManager(), "SUBLIME_PICKER");
    }

    @Override
    public void showThemeChoose() {
        AlertDialog.Builder builder = DialogUtils.makeDialogBuilder(this);
        builder.setTitle(R.string.change_theme);
        Integer[] res = new Integer[]{R.drawable.red_round, R.drawable.brown_round, R.drawable.blue_round,
                R.drawable.blue_grey_round, R.drawable.yellow_round, R.drawable.deep_purple_round,
                R.drawable.pink_round, R.drawable.green_round};
        List<Integer> list = Arrays.asList(res);
        ColorsListAdapter adapter = new ColorsListAdapter(this, list);
        adapter.setCheckItem(ThemeUtils.getCurrentTheme(this).getIntValue());
        GridView gridView = (GridView) LayoutInflater.from(this).inflate(R.layout.colors_panel_layout, null);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setCacheColorHint(0);
        gridView.setAdapter(adapter);
        builder.setView(gridView);
        final AlertDialog dialog = builder.create();
        dialog.show();
        gridView.setOnItemClickListener(presenter.getThemeChooseItemListener());
    }

    @Override
    public void saveAndNotifyThemeChange(int position) {
        int value = ThemeUtils.getCurrentTheme(this).getIntValue();
        if (value != position) {
            PreferenceUtils.getInstance(this).saveParam(getString(R.string.change_theme_key), position);
        }
        EventBus.getDefault().post(new BusEvent(BusEvent.TYPE_CHANGE_THEME));
    }


    @Override
    public void startWeatherActivity() {
        startActivity(new Intent(this, WeatherActivity.class));
    }

    @Override
    public void startFoodsCollectedActivity() {
        startActivity(new Intent(this, FoodsCollectedActivity.class));
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


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onMessage(String msg) {

    }
}
