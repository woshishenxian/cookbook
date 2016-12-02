package com.nanke.cook.ui.main.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.kevin.wraprecyclerview.WrapRecyclerView;
import com.nanke.cook.base.BaseRecyclerViewAdapter;
import com.nanke.cook.R;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.main.adapter.FoodsRecyclerViewAdapter;
import com.nanke.cook.entity.Food;
import com.nanke.cook.utils.LogUtils;
import com.nanke.cook.view.AutoSwipeRefreshLayout;
import com.nanke.cook.view.FooterViewFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vince on 16/10/25.
 */

public class FoodsFragment extends Fragment implements FoodsContract.View, FooterViewFactory.OnMoreRefreshListener, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.recycleView)
    WrapRecyclerView recycleView;
    @InjectView(R.id.swipeRefreshLayout)
    AutoSwipeRefreshLayout swipeRefreshLayout;

    FooterViewFactory footerViewFactory;


    private FoodsRecyclerViewAdapter foodsAdapter;

    private FoodsPresenter foodsPresenter;
    private int id;
    private int page = 1;
    private List<Food> foods;

    private BaseActivity mActivity;

    public FoodsFragment() {
    }

    public static Fragment newInstance(int arg) {
        FoodsFragment fragment = new FoodsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ID", arg);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wraprecycler, null);
        ButterKnife.inject(this, view);
        initFragment();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodsPresenter = new FoodsPresenter(this);
        onRefresh();
    }

    private void initFragment() {
        this.id = getArguments().getInt("ID");
        foods = new ArrayList<>();
        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recycleView.setLayoutManager(layoutManager);
        foodsAdapter = new FoodsRecyclerViewAdapter(foods);
        foodsAdapter.setOnInViewClickListener(R.id.foods_item_root, new BaseRecyclerViewAdapter.onInternalClickListenerImpl<Food>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, Food values) {
                super.OnClickListener(parentV, v, position, values);
                //添加跳转
                LogUtils.i("food_turn",position+"");
                foodsPresenter.onRecyclerViewItemClick(mActivity, values);
            }
        });

        foodsAdapter.setOnInViewClickListener(R.id.food_more, new BaseRecyclerViewAdapter.onInternalClickListenerImpl<Food>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, Food values) {
                super.OnClickListener(parentV, v, position, values);
                //更多菜单
                foodsPresenter.showPopMenu(v, position, values);
            }
        });

        recycleView.setAdapter(foodsAdapter);
        footerViewFactory = new FooterViewFactory(mActivity, this);
        recycleView.addFooterView(footerViewFactory.getFooterView());

    }


    @Override
    public void showPopMenu(View view, final Food food) {
        PopupMenu popup = new PopupMenu(mActivity, view);
        popup.getMenuInflater()
                .inflate(R.menu.menu_foods_more, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return foodsPresenter.onPopupMenuClick(mActivity,item.getItemId(), food);
            }
        });
        popup.setGravity(Gravity.RIGHT);
        popup.show();

    }

    @Override
    public void onRefresh() {
        page = 1;
        foodsPresenter.getFoods(id, page);
    }

    @Override
    public void onLoad() {
        page++;
        foodsPresenter.getFoods(id, page);
    }

    @Override
    public void loadFoods(List<Food> foods) {
        if (page == 1) {
            foodsAdapter.setList(foods);
        } else {
            foodsAdapter.addList(foods);
        }
        foodsAdapter.notifyDataSetChanged();

    }

    @Override
    public void showLoading() {
        if (page == 1) {
            swipeRefreshLayout.startRefreshing();
        }
    }

    @Override
    public void hideLoading() {
        if(footerViewFactory.isRefreshing()){
            footerViewFactory.setRefreshing(false);
        }
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.stopRefreshing();
        }
    }

    @Override
    public void onMessage(String msg) {
        mActivity.toast(msg);
    }
}
