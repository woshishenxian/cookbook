package com.nanke.cook.source;

import android.content.Context;

import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.Food;
import com.nanke.cook.source.impl.FoodsDataSourceImpl;

import java.io.IOException;
import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class FoodsDataRepository implements FoodsDataSource {

    private FoodsDataSource foodsDataSource;

    public FoodsDataRepository() {
        this.foodsDataSource = new FoodsDataSourceImpl();
    }

    @Override
    public void getFoods(int id, int page, ArrCallBack<Food> callBack) {
        foodsDataSource.getFoods(id,page,callBack);
    }

    @Override
    public void getCategory(Context cotnext, ArrCallBack<Category> callBack) {
            foodsDataSource.getCategory(cotnext,callBack);
    }

    @Override
    public void getFoodById(int id, ObjCallBack<Food> callBack) {
        foodsDataSource.getFoodById(id,callBack);
    }

    @Override
    public void getFoodByName(String name, ObjCallBack<Food> callBack) {
        foodsDataSource.getFoodByName(name, callBack);
    }

    @Override
    public void collectFood(Food food) {
        foodsDataSource.collectFood(food);
    }

    @Override
    public void delCollectedFood(Food food) {
        foodsDataSource.delCollectedFood(food);
    }

    @Override
    public List<Food> queryCollectedFoodByPage(int page) {
        return foodsDataSource.queryCollectedFoodByPage(page);
    }

    @Override
    public void clearCollectedFoods() {
        foodsDataSource.clearCollectedFoods();
    }
}
