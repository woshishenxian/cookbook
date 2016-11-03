package com.nanke.cook.source.impl;

import android.content.Context;

import com.google.gson.Gson;
import com.nanke.cook.api.FoodsService;
import com.nanke.cook.api.RetrofitManager;
import com.nanke.cook.db.DBManager;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.CategoryData;
import com.nanke.cook.entity.Food;
import com.nanke.cook.entity.FoodsData;
import com.nanke.cook.source.ArrCallBack;
import com.nanke.cook.source.FoodsDataSource;
import com.nanke.cook.source.ObjCallBack;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vince on 16/10/25.
 */

public class FoodsDataSourceImpl implements FoodsDataSource {

    FoodsService foodsService;

    public FoodsDataSourceImpl() {
        this.foodsService = RetrofitManager.getInstance().getRetrofit().create(FoodsService.class);
    }

    @Override
    public void getFoods(int id, int page, final ArrCallBack<Food> callBack) {
        callBack.start();
        foodsService.getFoods(id, page)
                .enqueue(new Callback<FoodsData>() {
                    @Override
                    public void onResponse(Call<FoodsData> call, Response<FoodsData> response) {
                        if(response.isSuccessful()){
                            FoodsData foodsData = response.body();
                            //对数据的处理操作
                            callBack.onTasksLoaded(foodsData.getTngou());
                        }else{
                            //请求出现错误例如：404 或者 500
                            callBack.onDataNotAvailable("网络异常");
                        }
                        callBack.onComplete();
                    }

                    @Override
                    public void onFailure(Call<FoodsData> call, Throwable t) {
                        callBack.onDataNotAvailable(t.getMessage());
                        callBack.onComplete();
                    }
                });
    }

    @Override
    public void getCategory(Context cotnext, ArrCallBack<Category> callBack) {

        try {
            InputStream inputStream = cotnext.getAssets().open("category.txt");
            CategoryData categoryData = new Gson().fromJson(new InputStreamReader(inputStream),CategoryData.class);
            callBack.onTasksLoaded(categoryData.getTngou());
        } catch (IOException e) {
            callBack.onDataNotAvailable("解析错误");
        }
    }


    @Override
    public void getFoodById(int id, final ObjCallBack<Food> callBack) {
        callBack.start();
        foodsService.getFoodById(id).enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {
                if(response.isSuccessful()){
                    callBack.onTasksLoaded(response.body());
                }else{
                    callBack.onDataNotAvailable("网络异常");
                }
                callBack.onComplete();
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {
                callBack.onDataNotAvailable(t.getMessage());
                callBack.onComplete();
            }
        });
    }

    @Override
    public void getFoodByName(String name,final ObjCallBack<Food> callBack) {
        callBack.start();
        foodsService.getFoodByName(name).enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {
                if(response.isSuccessful()){
                    callBack.onTasksLoaded(response.body());
                }else{
                    callBack.onDataNotAvailable("网络异常");
                }
                callBack.onComplete();
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {
                callBack.onDataNotAvailable(t.getMessage());
                callBack.onComplete();
            }
        });
    }

    @Override
    public void collectFood(Food food) {
        DBManager.getInstance().insertFood(food);
    }

    @Override
    public void delCollectedFood(Food food) {
        DBManager.getInstance().deleteFood(food);
    }

    @Override
    public List<Food> queryCollectedFoodByPage(int page) {
        return DBManager.getInstance().queryFoodsByPage(page);
    }

    @Override
    public void clearCollectedFoods() {
        DBManager.getInstance().clearFoods();
    }
}
