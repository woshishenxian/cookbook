package com.nanke.cook.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.nanke.cook.R;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.main.adapter.MainViewPageAdapter;
import com.nanke.cook.domain.Category;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements MainContract.View{

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        MainPresenter mainPresenter = new MainPresenter(this);
        mainPresenter.getCategory(this);

    }


    @Override
    public void loadCategory(List<Category> categories) {
        initMain(categories);
    }

    @Override
    public void showLoading() {
        showLoading();
    }

    @Override
    public void hideLoading() {
        hideLoading();
    }

    @Override
    public void onError(String msg) {
        toast(msg);
    }

    private void initMain(List<Category> categories){
        viewPager.setAdapter(new MainViewPageAdapter(getSupportFragmentManager(),categories));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }

}
