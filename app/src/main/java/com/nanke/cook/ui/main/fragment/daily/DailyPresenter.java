package com.nanke.cook.ui.main.fragment.daily;

import android.content.Context;
import android.content.Intent;

import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.Food;
import com.nanke.cook.source.ArrCallBack;
import com.nanke.cook.source.FoodsDataRepository;
import com.nanke.cook.ui.detail.FoodDetailActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by vince on 16/11/7.
 */

public class DailyPresenter implements DailyContract.Presenter {

    private DailyContract.View view;

    private FoodsDataRepository foodsDataRepository;

    private Category category; //今日主题
    private int curDay = -1;


    public DailyPresenter(DailyContract.View view) {
        this.view = view;
        this.foodsDataRepository = new FoodsDataRepository();

    }

    @Override
    public void getDialyFoods(Context context) {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        if(curDay != day){
            curDay = day;
            foodsDataRepository.getCategory(context, CategoryCallBack);
        }
    }


    @Override
    public boolean onPopupMenuClick(Context context, int id) {
        return false;
    }

    @Override
    public void onSwipeViewItemClick(Context context, Food food) {
        Intent intent = new Intent(context, FoodDetailActivity.class);
        intent.putExtra("ID", food.getId());
        context.startActivity(intent);
    }

    @Override
    public void onCollectItemClick(Food food) {
        foodsDataRepository.collectFood(food);
        view.onError("收藏成功");
    }

    private ArrCallBack<Food> DailyFoodsCallBack = new ArrCallBack<Food>() {
        @Override
        public void onTasksLoaded(List<Food> tasks) {
            view.loadFoods(tasks);
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
    };

    private ArrCallBack<Category> CategoryCallBack = new ArrCallBack<Category>() {
        @Override
        public void onTasksLoaded(List<Category> tasks) {

            category = tasks.get(curDay %tasks.size());
            foodsDataRepository.getFoods(category.getId(), 1, DailyFoodsCallBack);
        }

        @Override
        public void onDataNotAvailable(String msg) {
            view.onError(msg);
        }

        @Override
        public void start() {
        }

        @Override
        public void onComplete() {
        }
    };

    public  String getCurTheme(){
        return  "今日主题: "+category.getName();
    }


}
