package com.nanke.cook.base.impl;

import com.nanke.cook.base.BaseView;

/**
 * Created by vince on 16/11/11.
 */

public interface BaseViewImpl extends BaseView {

     void showLoading();

     void hideLoading();

     void onMessage(String msg);
}
