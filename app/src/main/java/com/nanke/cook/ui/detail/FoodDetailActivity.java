package com.nanke.cook.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.entity.Food;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.view.AutoSwipeRefreshLayout;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by vince on 16/10/26.
 */

public class FoodDetailActivity extends BaseActivity implements FoodDetailContract.View , SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.swipeRefreshLayout)
    AutoSwipeRefreshLayout swipeRefreshLayout;

    @InjectView(R.id.backdrop)
    ImageView backdrop;

    @InjectView(R.id.summaryText)
    TextView summaryText;

    @InjectView(R.id.keywordsText)
    TextView keywordsText;

    @InjectView(R.id.messageText)
    TextView messageText;

    @InjectView(R.id.diseaseText)
    TextView diseaseText;

    @InjectView(R.id.btn_collect)
    FloatingActionButton btn_collect;

    private FoodDetailPresenter foodDetailPresenter;

    private Food food; //当前食品


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        ButterKnife.inject(this);


        foodDetailPresenter = new FoodDetailPresenter(this);
        initToolbar();

        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();

    }

    @OnClick(R.id.btn_collect)
    public void onFabClick() {
        if (food != null){
            foodDetailPresenter.collectFood(food);
        }
    }


    private void initToolbar(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.menu_food_detail_more);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                foodDetailPresenter.onMenuItemClick(FoodDetailActivity.this,item.getItemId(),food);
                return false;
            }
        });
    }


    @Override
    public void onRefresh() {
        foodDetailPresenter.getFoodById(getIntent().getIntExtra("ID", 1));
    }

    @Override
    public void loadFoodDetail(Food food) {
        this.food = food;
        Picasso.with(this).load(food.getImgUrl()).into(backdrop);
        keywordsText.setText(food.getKeywords());
        summaryText.setText(food.getSummary());
        messageText.setText(food.getMessage());
        diseaseText.setText(food.getDisease());
        toolbar.setTitle(food.getName());
    }

    @Override
    public void onError(String msg) {
        toast(msg);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.startRefreshing();
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.stopRefreshing();
    }
}
