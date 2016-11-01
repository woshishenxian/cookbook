package com.nanke.cook.ui.main;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.nanke.cook.R;
import com.nanke.cook.entity.Category;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.main.adapter.MainViewPageAdapter;
import com.nanke.cook.ui.main.fragment.MainFragment;
import com.nanke.cook.ui.main.fragment.MineFragment;
import com.nanke.cook.view.indicator.BaseIconFragment;
import com.nanke.cook.view.indicator.BaseViewPager;
import com.nanke.cook.view.indicator.IconTabPageIndicator;
import com.nanke.cook.view.indicator.TabPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.indicator)
    IconTabPageIndicator indicator;
    @InjectView(R.id.viewpager)
    BaseViewPager viewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);


        viewPager.setNoScroll(true);

        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(),
                initFragmentList()));
        indicator.setViewPager(viewPager);

    }

    private  List<BaseIconFragment> initFragmentList() {
        List<BaseIconFragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new MineFragment());
        return fragments;
    }


}
