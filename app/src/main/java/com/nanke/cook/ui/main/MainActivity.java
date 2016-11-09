package com.nanke.cook.ui.main;

import android.os.Bundle;

import com.nanke.cook.R;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.main.fragment.home.HomeFragment;
import com.nanke.cook.ui.main.fragment.daily.DailyFragment;
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

    BackPressedInterface mBackPressedInterface;


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
        fragments.add(new HomeFragment());
        fragments.add(new DailyFragment());
        return fragments;
    }


    @Override
    public void onBackPressed() {
        if(mBackPressedInterface.onBackPressed()){
            return;
        }
        super.onBackPressed();
    }

    public void setmBackPressedInterface(BackPressedInterface mBackPressedInterface){
        this.mBackPressedInterface = mBackPressedInterface;
    }
}
