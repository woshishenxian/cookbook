package com.nanke.cook.ui.detail;

import android.content.Context;
import android.content.Intent;

import com.nanke.cook.R;
import com.nanke.cook.entity.Food;
import com.nanke.cook.source.FoodsDataRepository;
import com.nanke.cook.source.FoodsDataSource;
import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.ui.collect.FoodsCollectedActivity;

/**
 * Created by admin on 16/10/26.
 */
public class FoodDetailPresenter implements FoodDetailContract.Presenter, ObjCallBack<Food> {

    private FoodDetailContract.View view;

    private FoodsDataRepository repository;

    public FoodDetailPresenter(FoodDetailContract.View view) {
        this.view = view;
        this.repository = new FoodsDataRepository();
    }

    @Override
    public void getFoodById(int id) {
        repository.getFoodById(id, this);
    }

    @Override
    public void onTasksLoaded(Food tasks) {
        view.loadFoodDetail(tasks);
    }

    @Override
    public void onDataNotAvailable(String msg) {
        view.onError(msg);
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
    public void collectFood(Food food) {
        repository.collectFood(food);
        view.onError("收藏成功");
    }

    @Override
    public boolean onMenuItemClick(Context context, int id , Food food) {
        if (id == R.id.collect) {
            context.startActivity(new Intent(context, FoodsCollectedActivity.class));
        } else if (id == R.id.relate) {
            view.onError("跳转到食谱列表");
        }
        return true;
    }

    @Override
    public void getFoodByName(String name) {
        repository.getFoodByName(name,this);
    }
}
