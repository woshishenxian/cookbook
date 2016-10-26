package com.nanke.cook;

/**
 * Created by vince on 16/10/25.
 */

public interface BaseView {

    void showLoading();
    void hideLoading();

    void onError(String msg);
}
