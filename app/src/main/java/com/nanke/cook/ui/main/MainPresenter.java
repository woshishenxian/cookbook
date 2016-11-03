package com.nanke.cook.ui.main;

import android.content.Context;

import com.nanke.cook.entity.Category;
import com.nanke.cook.source.ArrCallBack;
import com.nanke.cook.source.FoodsDataRepository;
import com.nanke.cook.source.FoodsDataSource;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class MainPresenter implements MainContract.Presenter, ArrCallBack<Category> {

    FoodsDataRepository foodsDataRepository;
    MainContract.View view;


    public MainPresenter(MainContract.View view) {
        this.foodsDataRepository = new FoodsDataRepository();
        this.view = view;
    }

    @Override
    public void getCategory(Context cotnext) {
        foodsDataRepository.getCategory(cotnext, this);
    }

    @Override
    public void onTasksLoaded(List<Category> tasks) {
        view.loadCategory(tasks);
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
