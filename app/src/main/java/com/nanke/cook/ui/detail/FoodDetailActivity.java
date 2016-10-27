package com.nanke.cook.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.domain.Food;
import com.nanke.cook.ui.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vince on 16/10/26.
 */

public class FoodDetailActivity extends BaseActivity implements FoodDetailContract.View{

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.backdrop)
    ImageView backdrop;

    @InjectView(R.id.summaryText)
    TextView summaryText;

    @InjectView(R.id.keywordsText)
    TextView keywordsText;

    @InjectView(R.id.messageText)
    TextView messageText;

    @InjectView(R.id.diseaseText)
    TextView diseaseText;

    private FoodDetailPresenter foodDetailPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        ButterKnife.inject(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        foodDetailPresenter = new FoodDetailPresenter(this);
        foodDetailPresenter.getFoodById(getIntent().getIntExtra("ID",1));
    }

    @Override
    public void loadFoodDetail(Food food) {
        Picasso.with(this).load(food.getImg()).into(backdrop);
        keywordsText.setText(food.getKeywords());
        summaryText.setText(food.getSummary());
        messageText.setText(food.getMessage());
        diseaseText.setText(food.getDisease());
        toolbar.setTitle(food.getName());
    }

    @Override
    public void onError(String msg) {
        toast(msg);
    }
}
