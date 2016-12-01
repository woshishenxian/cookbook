package com.nanke.cook.ui.splash;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;

import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.base.BaseView;
import com.nanke.cook.base.impl.BaseViewImpl;

/**
 * Created by vince on 16/11/22.
 */

public class SplashContract {

    interface View extends BaseView {

        void startMainActivity();
    }

    interface Presenter extends BasePresenter {

        void initCityData(Context context);

    }
}
