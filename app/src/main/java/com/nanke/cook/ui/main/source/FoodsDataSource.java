package com.nanke.cook.ui.main.source;

import android.content.Context;

import com.nanke.cook.ui.main.domain.Category;
import com.nanke.cook.ui.main.domain.Food;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public interface FoodsDataSource {

    public interface ArrCallBack<T>{
        void onTasksLoaded(List<T> tasks);

        void onDataNotAvailable(String msg);

        void start();

        void onComplete();
    }

    public interface ObjCallBack<T>{
        void onTasksLoaded(T tasks);

        void onDataNotAvailable(String msg);

        void start();

        void onComplete();
    }



    public void getFoods(int id ,int page,ArrCallBack<Food> callBack);


    public void getCategory(Context cotnext, ArrCallBack<Category> callBack);

    public void getFoodById(Context cotnext, ArrCallBack<Category> callBack);
}
