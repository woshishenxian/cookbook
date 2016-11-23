package com.nanke.cook.ui.weather;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.nanke.cook.R;
import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.ui.BaseActivity;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by vince on 16/11/7.
 */

public class WeatherActivity extends BaseActivity<WeatherPresenter> implements WeatherContract.View{


    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.webView)
    WebView webView;

    @InjectView(R.id.prgressBar)
    ProgressBar progressBar;

    Map<String,String> map = new HashMap<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new ProgressWebChromeClient(progressBar));
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });

        presenter.getWeather(this);
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_weather;
    }

    @Override
    public WeatherPresenter initPresenter() {
        return new WeatherPresenter(this);
    }

    @Override
    public void initToolbar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void loadWeatherWeb(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onMessage(String msg) {
        toast(msg);
    }

    public class ProgressWebChromeClient extends android.webkit.WebChromeClient {

        ProgressBar progressbar;
        public ProgressWebChromeClient(ProgressBar progressbar) {
            super();
            this.progressbar = progressbar;
            this.progressbar.setMax(100);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (progressbar.getVisibility() == View.GONE)
                progressbar.setVisibility(View.VISIBLE);
            progressbar.setProgress(newProgress);
            if (newProgress >= 100) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        progressbar.setVisibility(View.GONE);
                    }
                }, 500);
            }
            super.onProgressChanged(view, newProgress);
        }


    }


}
