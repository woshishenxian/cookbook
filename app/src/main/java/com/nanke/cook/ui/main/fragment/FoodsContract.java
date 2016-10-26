package com.nanke.cook.ui.main.fragment;

import android.content.Context;

import com.nanke.cook.BasePresenter;
import com.nanke.cook.BaseView;
import com.nanke.cook.domain.Food;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class FoodsContract {

    public interface View extends BaseView {

        void loadFoods(List<Food> foods);

        void showPopMenu(android.view.View view, Food food);

    }

    public interface Presenter extends BasePresenter{
        void getFoods(int id,int page);

        void showPopMenu(android.view.View v, Integer position, Food food);

        boolean onPopupMenuClick(int id, Food food);

        void onRecyclerViewItemClick(Context context, Food food);
    }
}
