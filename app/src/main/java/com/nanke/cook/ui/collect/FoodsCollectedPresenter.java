package com.nanke.cook.ui.collect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.nanke.cook.R;
import com.nanke.cook.entity.Food;
import com.nanke.cook.source.FoodsDataRepository;
import com.nanke.cook.source.FoodsDataSource;
import com.nanke.cook.ui.detail.FoodDetailActivity;

import java.util.List;

/**
 * Created by admin on 16/10/26.
 */
public class FoodsCollectedPresenter implements FoodsCollectedContract.Presenter{

    private FoodsCollectedContract.View view;

    private FoodsDataRepository foodsDataRepository;

    private Handler mHandler = new Handler();

    public FoodsCollectedPresenter(FoodsCollectedContract.View view) {
        this.view = view;
        foodsDataRepository = new FoodsDataRepository();
    }


    @Override
    public void getCollectedFoods(final int page) {
        view.showLoading();
        view.loadCollectedFoods(foodsDataRepository.queryCollectedFoodByPage(page));
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.hideLoading();
            }
        },1000);

    }

    @Override
    public void showPopMenu(View v, Integer position, Food food) {
        view.showPopMenu(v, food);
    }

    @Override
    public boolean onPopupMenuClick(Context context,int id, Food food) {
        if(id == R.id.collect){
            food.setIsCollected(true);
            foodsDataRepository.collectFood(food);
            view.onMessage("收藏成功");
        }else if(id == R.id.relate) {
            view.onMessage("跳转到食谱列表");
        }else if(id == R.id.collect_cancel){
            food.setIsCollected(false);
            foodsDataRepository.delCollectedFood(food);
            view.onMessage("取消收藏");
        }
        return true;
    }

    @Override
    public void onRecyclerViewItemClick(Context context, Food food) {
        Intent intent = new Intent(context, FoodDetailActivity.class);
        intent.putExtra("ID", food.getId());
        context.startActivity(intent);
    }


    @Override
    public boolean onMenuItemClick(Context context,int itemId) {
        if(itemId == R.id.collect_clear){
            clearCollectedFoods(context);
        }
        return true;
    }

    private void clearCollectedFoods(Context context) {
        String[] btns = {  "取消", "清空" };
        MaterialDialog dialog = new MaterialDialog(context);
        dialog.title("温馨提示")
                .content("确定要清空收藏夹吗?")
                .btnText(btns).widthScale(0.7f).show();

        dialog.setOnBtnClickL(null,new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                foodsDataRepository.clearCollectedFoods();
                getCollectedFoods(1);
            }
        });


    }
}
