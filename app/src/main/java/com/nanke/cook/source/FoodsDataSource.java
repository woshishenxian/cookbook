package com.nanke.cook.source;

import android.content.Context;

import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.Food;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public interface FoodsDataSource {




    public void getFoods(int id ,int page,ArrCallBack<Food> callBack);

    public void getFoodById(int id ,ObjCallBack<Food> callBack);

    public void getFoodByName(String name ,ObjCallBack<Food> callBack);

    public void getCategory(Context cotnext, ArrCallBack<Category> callBack);

    public void collectFood(Food food);

    public void delCollectedFood(Food food);

    public List<Food> queryCollectedFoodByPage(int page);

    public void clearCollectedFoods();
}
