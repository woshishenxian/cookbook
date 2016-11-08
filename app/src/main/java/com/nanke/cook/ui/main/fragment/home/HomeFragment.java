package com.nanke.cook.ui.main.fragment.home;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
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

import com.nanke.cook.R;
import com.nanke.cook.entity.Category;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.main.MainContract;
import com.nanke.cook.ui.main.MainPresenter;
import com.nanke.cook.ui.main.adapter.MainViewPageAdapter;
import com.nanke.cook.view.indicator.BaseIconFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vince on 16/10/31.
 */

public class HomeFragment extends BaseIconFragment implements MainContract.View{

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;

    private BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,null);
        ButterKnife.inject(this,view);

        initToolbar();

        MainPresenter mainPresenter = new MainPresenter(this);
        mainPresenter.getCategory(mActivity);
        return view;
    }

    private void initToolbar(){
        toolbar.inflateMenu(R.menu.menu_main_more);
        SearchManager searchManager =
                (SearchManager) mActivity.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(toolbar.getMenu().findItem(R.id.search));
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(mActivity.getComponentName()));
        searchView.setQueryHint(getString(R.string.search_hint));

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
//        MenuItemCompat.setOnActionExpandListener(searchItem, mainPresenter);
    }

    private void initMain(List<Category> categories){
        viewPager.setAdapter(new MainViewPageAdapter(mActivity.getSupportFragmentManager(),categories));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void loadCategory(List<Category> categories) {
        initMain(categories);
    }

    @Override
    public void showLoading() {
//        showLoading();
    }

    @Override
    public void hideLoading() {
//        hideLoading();
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
}
