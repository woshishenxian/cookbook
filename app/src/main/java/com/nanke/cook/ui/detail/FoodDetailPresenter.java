package com.nanke.cook.ui.detail;

import com.nanke.cook.domain.Food;
import com.nanke.cook.source.FoodsDataRepository;
import com.nanke.cook.source.FoodsDataSource;

/**
 * Created by admin on 16/10/26.
 */
public class FoodDetailPresenter implements FoodDetailContract.Presenter , FoodsDataSource.ObjCallBack<Food>{

    private FoodDetailContract.View view;

    private FoodsDataRepository repository;

    public FoodDetailPresenter(FoodDetailContract.View view) {
        this.view = view;
        this.repository= new FoodsDataRepository();
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
}
