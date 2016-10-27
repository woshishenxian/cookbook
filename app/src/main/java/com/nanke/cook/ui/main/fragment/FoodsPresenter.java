package com.nanke.cook.ui.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.nanke.cook.domain.Food;
import com.nanke.cook.source.FoodsDataRepository;
import com.nanke.cook.source.FoodsDataSource;
import com.nanke.cook.ui.detail.FoodDetailActivity;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class FoodsPresenter implements FoodsContract.Presenter,FoodsDataSource.ArrCallBack<Food>{

    private FoodsContract.View view;
    private FoodsDataRepository foodsDataRepository;

    public FoodsPresenter(FoodsContract.View view) {
        this.view  = view;
        foodsDataRepository = new FoodsDataRepository();

    }

    @Override
    public void getFoods(int id, int page) {

        foodsDataRepository.getFoods(id,page,this);
    }

    @Override
    public void start() {
        view.showLoading();
    }

    @Override
    public void onComplete() {
        view.hideLoading();
    }

    @Override
    public void onTasksLoaded(List<Food> tasks) {
        view.loadFoods(tasks);
    }

    @Override
    public void onDataNotAvailable(String msg) {
        view.onError(msg);
    }


    @Override
    public void showPopMenu(View v, Integer position, Food food) {
        view.showPopMenu(v,food);
    }

    @Override
    public boolean onPopupMenuClick(int id, Food food) {
        return false;
    }

    @Override
    public void onRecyclerViewItemClick(Context context, Food food) {
        Intent intent = new Intent(context, FoodDetailActivity.class);
        intent.putExtra("ID",food.getId());
        context.startActivity(intent);
    }
}