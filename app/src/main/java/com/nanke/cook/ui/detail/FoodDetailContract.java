package com.nanke.cook.ui.detail;

import android.content.Context;

import com.nanke.cook.BasePresenter;
import com.nanke.cook.BaseView;
import com.nanke.cook.entity.Food;

/**
 * Created by admin on 16/10/26.
 */
public class FoodDetailContract {

    public interface View extends BaseView {

        void loadFoodDetail(Food food);

    }

    public interface Presenter extends BasePresenter {
        void getFoodById(int id);

        void collectFood(Food food);

        boolean onMenuItemClick(Context context, int id , Food food);
    }
}
