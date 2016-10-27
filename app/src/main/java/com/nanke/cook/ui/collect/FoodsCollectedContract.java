package com.nanke.cook.ui.collect;

import android.content.Context;

import com.nanke.cook.BasePresenter;
import com.nanke.cook.BaseView;
import com.nanke.cook.entity.Food;

import java.util.List;

/**
 * Created by admin on 16/10/26.
 */
public class FoodsCollectedContract {

    public interface View extends BaseView {

        void loadCollectedFoods(List<Food> foods);

        void showPopMenu(android.view.View view, Food food);

    }

    public interface Presenter extends BasePresenter{
        void getCollectedFoods(int page);

        void showPopMenu(android.view.View v, Integer position, Food food);

        boolean onPopupMenuClick(Context context,int id, Food food);

        void onRecyclerViewItemClick(Context context, Food food);

        boolean onMenuItemClick(int itemId);

    }
}
