package com.nanke.cook.ui.main.fragment.home;

import android.content.Context;
import android.view.MenuItem;

import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.base.BaseView;
import com.nanke.cook.base.impl.BaseViewImpl;
import com.nanke.cook.entity.Category;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class HomeContract {

    public interface View extends BaseView {

        void initToolbar();

        void loadCategory(List<Category> categories);

        void turnToSearch();
    }

    public interface Presenter extends BasePresenter{
        void getCategory(Context cotnext);

        void onMenuItemClick(MenuItem menuItem);
    }


}
