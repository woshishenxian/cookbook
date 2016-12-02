package com.nanke.cook.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.ActionMode;

import com.nanke.cook.R;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.main.MainActivity;

/**
 * Created by vince on 16/11/22.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    private Handler handler = new Handler();


    @Override
    protected int getLayoutView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       presenter.initCityData(this);
    }


    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter(this);
    }



    @Override
    public void startMainActivity() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

}
