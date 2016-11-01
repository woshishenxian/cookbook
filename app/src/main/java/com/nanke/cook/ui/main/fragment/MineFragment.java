package com.nanke.cook.ui.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanke.cook.R;
import com.nanke.cook.view.indicator.BaseIconFragment;

import butterknife.ButterKnife;

/**
 * Created by vince on 16/10/31.
 */

public class MineFragment extends BaseIconFragment{


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,null);

        ButterKnife.inject(this,view);

        return view;
    }

    @Override
    public String getTitle() {
        return "我的";
    }

    @Override
    public int getIconId() {
        return R.drawable.tab_mine_selector;
    }
}
