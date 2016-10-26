package com.nanke.cook.ui.main.source.impl;

import android.content.Context;

import com.google.gson.Gson;
import com.nanke.cook.api.FoodsService;
import com.nanke.cook.api.RetrofitManager;
import com.nanke.cook.ui.main.domain.Category;
import com.nanke.cook.ui.main.domain.CategoryData;
import com.nanke.cook.ui.main.domain.Food;
import com.nanke.cook.ui.main.domain.FoodsData;
import com.nanke.cook.ui.main.source.FoodsDataSource;

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


    @Override
    public void getFoods(int id, int page, final ArrCallBack<Food> callBack) {
        callBack.start();
        FoodsService foodsService = RetrofitManager.getInstance().getRetrofit().create(FoodsService.class);
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
}
