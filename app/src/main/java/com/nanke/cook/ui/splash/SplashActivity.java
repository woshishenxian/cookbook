package com.nanke.cook.ui.splash;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.ui.main.MainActivity;
import com.nanke.cook.ui.weather.WeatherPresenter;
import com.nanke.cook.utils.BlurUtil;

import org.w3c.dom.Text;

import butterknife.InjectView;

/**
 * Created by vince on 16/11/22.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View ,
        ActivityCompat.OnRequestPermissionsResultCallback{

    @InjectView(R.id.bgView)
    ImageView bgView;
    @InjectView(R.id.titleView)
    TextView titleView;

    private boolean save = false;
    private boolean anim = false;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBackground();

    }

    private void initBackground(){
//        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.splash);
//        Bitmap bitmap = BlurUtil.drawableToBitmap(drawable);
//        Bitmap blurBitmap = BlurUtil.blur(this,bitmap);
//        bgView.setImageBitmap(blurBitmap);
//
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_splash_bg);
        animation.setAnimationListener(presenter.getBackgroundAnimationListener());
        animation.setFillAfter(true);
        bgView.startAnimation(animation);

    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_splash;
    }

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    public void saveSuccess() {
        save = true;
        if(save && anim && !this.isFinishing()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void saveFailed(String msg) {
        toast(msg);
    }

    @Override
    public void backgroundAnimStart() {
        presenter.saveCityAndCode(this);
    }

    @Override
    public void backgroundAnimEnd() {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_splash_title);
        animation.setAnimationListener(presenter.getTitleAnimationListener());
        animation.setFillAfter(true);
        titleView.setVisibility(View.VISIBLE);
        titleView.startAnimation(animation);
    }

    @Override
    public void titleAnimEnd() {
        anim = true;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(save && anim && !SplashActivity.this.isFinishing()){

                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        },500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] paramArrayOfInt) {
        presenter.onRequestPermissionsResult(this,requestCode,paramArrayOfInt);
    }
}
