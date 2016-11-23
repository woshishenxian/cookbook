package com.nanke.cook.ui.splash;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.animation.Animation;

import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.base.BaseView;
import com.nanke.cook.base.impl.BaseViewImpl;

/**
 * Created by vince on 16/11/22.
 */

public class SplashContract {

    interface View extends BaseView {
        void saveSuccess();

        void saveFailed(String msg);

        void backgroundAnimStart();

        void backgroundAnimEnd();

        void titleAnimEnd();

    }

    interface Presenter extends BasePresenter {
        void saveCityAndCode(Context context);

        Animation.AnimationListener getBackgroundAnimationListener();

        Animation.AnimationListener getTitleAnimationListener();

        void onRequestPermissionsResult(Context context,int requestCode, int[] paramArrayOfInt);

        void onResume(Context context);
    }
}
