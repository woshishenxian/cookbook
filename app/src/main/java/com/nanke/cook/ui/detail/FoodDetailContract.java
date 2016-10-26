package com.nanke.cook.ui.detail;

import com.nanke.cook.BasePresenter;
import com.nanke.cook.BaseView;
import com.nanke.cook.domain.Food;

import java.util.List;

/**
 * Created by admin on 16/10/26.
 */
public class FoodDetailContract {

    public interface View extends BaseView {

        void loadFoodDetail(Food food);

    }

    public interface Presenter extends BasePresenter {
        void getFoodById(int id);

    }
}
