package com.nanke.cook.ui.main.fragment.daily;

import android.content.Context;

import com.nanke.cook.BasePresenter;
import com.nanke.cook.BaseView;
import com.nanke.cook.entity.Food;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class DailyContract {

    public interface View extends BaseView {

        void loadFoods(List<Food> foods);

        void turnToFoodsCollected();

        void showShareDialog();


    }

    public interface Presenter extends BasePresenter{
        void getDialyFoods(Context context);

        boolean onPopupMenuClick(Context context, int id);

        void onSwipeViewItemClick(Context context, Food food);

        void onCollectItemClick(Food food);
    }
}
