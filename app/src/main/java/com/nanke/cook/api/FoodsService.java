package com.nanke.cook.api;





import com.nanke.cook.entity.Food;
import com.nanke.cook.entity.FoodsData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vince on 16/10/25.
 */

public interface FoodsService {

    @GET("cook/list")
    Call<FoodsData> getFoods(@Query("id") int id, @Query("page") int page);

    @GET("cook/show")
    Call<Food> getFoodById(@Query("id") int id);

    @GET("cook/name")
    Call<Food> getFoodByName(@Query("name") String name);

}
