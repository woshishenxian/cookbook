package com.nanke.cook.api;


import com.nanke.cook.ui.main.domain.Food;
import com.nanke.cook.ui.main.domain.FoodsData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vince on 16/10/25.
 */

public interface FoodsService {

    @GET("food/list")
    Call<FoodsData> getFoods(@Query("id") int id,@Query("page") int page);

    @GET("food/show")
    Call<Food> getFood(@Query("id") int id);
}
