package com.nanke.cook.ui.detail;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.entity.Food;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.view.AutoSwipeRefreshLayout;
import com.squareup.picasso.Picasso;

import app.dinus.com.loadingdrawable.LoadingView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by vince on 16/10/26.
 */

public class FoodDetailActivity extends BaseActivity<FoodDetailPresenter> implements FoodDetailContract.View , SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.backdrop)
    ImageView backdrop;


    @InjectView(R.id.messageText)
    TextView messageText;


    @InjectView(R.id.btn_collect)
    FloatingActionButton btn_collect;


    private Food food; //当前食品

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onRefresh();

    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_food_detail;
    }

    @Override
    public FoodDetailPresenter initPresenter() {
        return new FoodDetailPresenter(this);
    }

    @OnClick(R.id.btn_collect)
    public void onFabClick() {
        if (food != null){
            presenter.collectFood(food);
        }
    }

    @Override
    public void initToolbar(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.menu_food_detail_more);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.onMenuItemClick(FoodDetailActivity.this,item.getItemId(),food);
                return false;
            }
        });
    }


    @Override
    public void onRefresh() {
        String name = getIntent().getStringExtra(SearchManager.QUERY);
        if(name !=null){
            presenter.getFoodByName(name);
        }else{
            presenter.getFoodById(getIntent().getIntExtra("ID", 1));
        }

    }

    @Override
    public void loadFoodDetail(Food food) {
        this.food = food;
        Picasso.with(this).load(food.getImgUrl()).into(backdrop);

        if(food.getMessage()!=null){
            messageText.setText(Html.fromHtml(food.getMessage()));
        }

        toolbar.setTitle(food.getName());
    }

    @Override
    public void onMessage(String msg) {
        toast(msg);
    }

    @Override
    public void showLoading() {
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("努力加载中...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }

    }

    @Override
    public void hideLoading() {
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
