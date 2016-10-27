package com.nanke.cook.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nanke.cook.entity.Category;
import com.nanke.cook.ui.main.fragment.FoodsFragment;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class MainViewPageAdapter extends FragmentPagerAdapter{

    private List<Category> categories;


    public MainViewPageAdapter(FragmentManager fm, List<Category> categories) {
        super(fm);
        this.categories = categories;
    }

    @Override
    public Fragment getItem(int position) {
        return FoodsFragment.newInstance(categories.get(position).getId());
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getTitle();
    }
}
