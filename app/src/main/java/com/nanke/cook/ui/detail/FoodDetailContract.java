package com.nanke.cook.ui.detail;

import android.content.Context;

import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.base.BaseView;
import com.nanke.cook.base.impl.BaseViewImpl;
import com.nanke.cook.entity.Food;

/**
 * Created by admin on 16/10/26.
 */
public class FoodDetailContract {

    public interface View extends BaseViewImpl {

        void loadFoodDetail(Food food);

    }

    public interface Presenter extends BasePresenter {
        void getFoodById(int id);

        void getFoodByName(String name);

        void collectFood(Food food);

        boolean onMenuItemClick(Context context, int id , Food food);
    }
}
