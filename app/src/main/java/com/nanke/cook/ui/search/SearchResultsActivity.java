package com.nanke.cook.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nanke.cook.BasePresenter;
import com.nanke.cook.R;
import com.nanke.cook.ui.BaseActivity;

/**
 * Created by admin on 16/10/29.
 */
public class SearchResultsActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public int getLayoutView() {
        return R.layout.activity_search;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initToolbar() {

    }
}
