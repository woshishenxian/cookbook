package com.nanke.cook.ui.collect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.kevin.wraprecyclerview.WrapRecyclerView;
import com.nanke.cook.BaseRecyclerViewAdapter;
import com.nanke.cook.R;
import com.nanke.cook.entity.Food;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.detail.FoodDetailActivity;
import com.nanke.cook.ui.main.adapter.FoodsRecyclerViewAdapter;
import com.nanke.cook.view.AutoSwipeRefreshLayout;
import com.nanke.cook.view.FooterViewFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vince on 16/10/27.
 */

public class FoodsCollectedActivity extends BaseActivity implements FoodsCollectedContract.View ,
        SwipeRefreshLayout.OnRefreshListener ,FooterViewFactory.OnMoreRefreshListener {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.recycleView)
    WrapRecyclerView recycleView;

    @InjectView(R.id.swipeRefreshLayout)
    AutoSwipeRefreshLayout swipeRefreshLayout;

    FooterViewFactory footerViewFactory;

    private FoodsRecyclerViewAdapter foodsAdapter;

    private FoodsCollectedPresenter foodCollectedPresenter;
    private int page = 1;
    private List<Food> foods;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_collected);

        ButterKnife.inject(this);

        initToolbar();
        initView();

        foodCollectedPresenter = new FoodsCollectedPresenter(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
    }

    private void initToolbar(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.menu_food_collected_tool);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return foodCollectedPresenter.onMenuItemClick(item.getItemId());
            }
        });
    }


    private void initView() {
        foods = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        foodsAdapter = new FoodsRecyclerViewAdapter(foods);
        foodsAdapter.setOnInViewClickListener(R.id.foods_item_root, new BaseRecyclerViewAdapter.onInternalClickListenerImpl<Food>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, Food values) {
                super.OnClickListener(parentV, v, position, values);
                //添加跳转
                foodCollectedPresenter.onRecyclerViewItemClick(FoodsCollectedActivity.this
                        , values);
            }
        });

        foodsAdapter.setOnInViewClickListener(R.id.food_more, new BaseRecyclerViewAdapter.onInternalClickListenerImpl<Food>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, Food values) {
                super.OnClickListener(parentV, v, position, values);
                //更多菜单
                foodCollectedPresenter.showPopMenu(v, position, values);
            }
        });

        recycleView.setAdapter(foodsAdapter);
        footerViewFactory = new FooterViewFactory(this, this);
        recycleView.addFooterView(footerViewFactory.getFooterView());

    }

    @Override
    public void onRefresh() {
        page = 1;
        foodCollectedPresenter.getCollectedFoods(page);
    }

    @Override
    public void onLoad() {
        page ++;
        foodCollectedPresenter.getCollectedFoods(page);
    }

    @Override
    public void loadCollectedFoods(List<Food> foods) {
        if(page == 1){
            foodsAdapter.setList(foods);
        }else{
            foodsAdapter.addList(foods);
        }
        foodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPopMenu(View view,final Food food) {
        PopupMenu popup = new PopupMenu(this, view);
        if(food.getIsCollected()){
            popup.getMenuInflater()
                    .inflate(R.menu.menu_food_collected_more, popup.getMenu());
        }else{
            popup.getMenuInflater()
                    .inflate(R.menu.menu_foods_more, popup.getMenu());
        }
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return foodCollectedPresenter.onPopupMenuClick(FoodsCollectedActivity.this,item.getItemId(),food);
            }
        });
        popup.setGravity(Gravity.RIGHT);
        popup.show();
    }

    @Override
    public void showLoading() {
        if(page == 1){
            swipeRefreshLayout.startRefreshing();
        }
    }

    @Override
    public void hideLoading() {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.stopRefreshing();
        }else {
            footerViewFactory.setRefreshing(false);
        }
    }

    @Override
    public void onError(String msg) {
        toast(msg);
    }
}
