package com.nanke.cook.ui.main.fragment.daily;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.entity.Food;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.main.adapter.DailyAdapter;
import com.nanke.cook.utils.LogUtils;
import com.nanke.cook.view.AutoSwipeRefreshLayout;
import com.nanke.cook.view.indicator.BaseIconFragment;
import com.nanke.cook.view.swipefling.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import app.dinus.com.loadingdrawable.LoadingView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vince on 16/10/31.
 */

public class DailyFragment extends BaseIconFragment implements DailyContract.View {


    private BaseActivity activity;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.theme)
    TextView theme;

    @InjectView(R.id.flingContainer)
    SwipeFlingAdapterView flingContainer;

    @InjectView(R.id.loadingView)
    LoadingView loadingView;

    private DailyAdapter dailyAdapter;
    private DailyPresenter dailyPresenter;

    private List<Food> oldfoods;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily, null);

        ButterKnife.inject(this, view);
        initToolbar();

        flingContainer.setFlingListener(onFlingListener);
        flingContainer.setOnItemClickListener(onItemClickListener);

        oldfoods = new ArrayList<>();
        dailyPresenter = new DailyPresenter(this);
        dailyPresenter.getDialyFoods(this.getContext());
        return view;
    }

    private void initToolbar() {
        toolbar.inflateMenu(R.menu.menu_weather_more);
        Menu menu = toolbar.getMenu();
        if(menu instanceof PopupMenu){
            ((PopupMenu) menu).setGravity(Gravity.BOTTOM);
        }
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dailyPresenter.onPopupMenuClick(activity, item.getItemId());
                return true;
            }
        });
    }


    @Override
    public void loadFoods(List<Food> foodList) {
        oldfoods.addAll(foodList);
        dailyAdapter = new DailyAdapter(this.getContext(), foodList);
        flingContainer.setAdapter(dailyAdapter);
        dailyAdapter.setOnCollectItemClickListener(onCollectItemClickListener);

        theme.setText(dailyPresenter.getCurTheme());
    }


    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void onError(String msg) {
        activity.toast(msg);
    }

    @Override
    public String getTitle() {
        return "每日推荐";
    }

    @Override
    public int getIconId() {
        return 0;
    }


    private SwipeFlingAdapterView.onFlingListener onFlingListener = new SwipeFlingAdapterView.onFlingListener() {
        @Override
        public void removeFirstObjectInAdapter() {
            dailyAdapter.remove(0);
        }

        @Override
        public void onLeftCardExit(Object dataObject) {

        }

        @Override
        public void onRightCardExit(Object dataObject) {

        }

        @Override
        public void onAdapterAboutToEmpty(int itemsInAdapter) {
            dailyAdapter.addAll(oldfoods);
        }

        @Override
        public void onScroll(float progress, float scrollXProgress) {

        }
    };

    private SwipeFlingAdapterView.OnItemClickListener onItemClickListener = new SwipeFlingAdapterView.OnItemClickListener() {
        @Override
        public void onItemClicked(MotionEvent event, View v, Object dataObject) {
            Food food = (Food) dataObject;
            dailyPresenter.onSwipeViewItemClick(getContext(), food);
        }
    };

    private DailyAdapter.OnCollectItemClickListener onCollectItemClickListener = new DailyAdapter.OnCollectItemClickListener() {
        @Override
        public void onItemClick(Food food) {
            dailyPresenter.onCollectItemClick(food);
        }
    };
}
