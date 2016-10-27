package com.nanke.cook.ui.main;

import android.content.Context;

import com.nanke.cook.BasePresenter;
import com.nanke.cook.BaseView;
import com.nanke.cook.entity.Category;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class MainContract {

    public interface View extends BaseView {

        void loadCategory(List<Category> categories);
    }

    public interface Presenter extends BasePresenter{
        void getCategory(Context cotnext);
    }
}
