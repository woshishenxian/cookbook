package com.nanke.cook.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nanke.cook.utils.SnackbarUtils;

/**
 * Created by vince on 16/10/25.
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void toast(String msg) {
        SnackbarUtils.show(this,msg);
    }
}
