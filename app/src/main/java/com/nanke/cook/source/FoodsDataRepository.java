package com.nanke.cook.source;

import android.content.Context;

import com.nanke.cook.domain.Category;
import com.nanke.cook.domain.Food;
import com.nanke.cook.source.impl.FoodsDataSourceImpl;

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
}
