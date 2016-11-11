package com.nanke.cook.ui.main.fragment.home;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.nanke.cook.R;
import com.nanke.cook.entity.Category;
import com.nanke.cook.entity.weather.Realtime;
import com.nanke.cook.source.ArrCallBack;
import com.nanke.cook.source.FoodsDataRepository;
import com.nanke.cook.source.ObjCallBack;
import com.nanke.cook.source.WeatherDataRepository;
import com.nanke.cook.ui.weather.fragment.SublimePickerFragment;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class HomePresenter implements HomeContract.Presenter {

    FoodsDataRepository foodsDataRepository;
    HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.foodsDataRepository = new FoodsDataRepository();
        this.view = view;
        this.view.initToolbar();
    }

    @Override
    public void getCategory(Context cotnext) {
        foodsDataRepository.getCategory(cotnext, categoryArrCallBack);
    }


    @Override
    public void onMenuItemClick(MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.search){
            view.turnToSearch();
        }
    }


    //分类数据回调
    private ArrCallBack<Category> categoryArrCallBack = new ArrCallBack<Category>() {
        @Override
        public void onTasksLoaded(List<Category> tasks) {
            view.loadCategory(tasks);
        }

        @Override
        public void onDataNotAvailable(String msg) {

        }

        @Override
        public void start() {

        }

        @Override
        public void onComplete() {

        }
    };



}
