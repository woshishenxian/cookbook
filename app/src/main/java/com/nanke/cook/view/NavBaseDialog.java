package com.nanke.cook.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceRightEnter;
import com.flyco.dialog.widget.base.BaseDialog;
import com.nanke.cook.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by vince on 16/12/1.
 */

public class NavBaseDialog extends BaseDialog<NavBaseDialog> {

    @InjectView(R.id.cityNameView)
    TextView cityNameView;
    @InjectView(R.id.temperatureView)
    TextView temperatureView;
    @InjectView(R.id.weather_img)
    ImageView weather_img;
    @InjectView(R.id.weather_refresh)
    ImageView weather_refresh;

    public NavBaseDialog(Context context) {
        super(context);
    }
    public NavBaseDialog(Context context, Callback callback) {
        super(context);
        this.callback = callback;
    }

    @Override
    public View onCreateView() {
        widthScale(0.7f);
        showAnim(new BounceRightEnter());

        View inflate = View.inflate(mContext, R.layout.panel_weather, null);
        ButterKnife.inject(this, inflate);

        return inflate;
    }

    @Override
    public void setUiBeforShow() {

    }

    public void setCityName(String cityName) {
        this.cityNameView.setText(cityName);
    }


    public void setTemperature(String temperature) {
        this.temperatureView.setText(temperature);
    }

    public void setWeather_img( int resId) {
        this.weather_img.setImageResource(resId);
    }


    @OnClick(R.id.tv_cancel)
    public void onCancelClick(){
        dismiss();
    }


    @OnClick(R.id.tv_enter)
    public void startWeatherActivity(){
        if(callback !=null){
            callback.rightClick();
        }
    }

    @OnClick(R.id.weather_content)
    public void refreshWeather(){
        if(callback !=null){
            callback.contentClick();
        }
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback{
        void contentClick();

        void rightClick();
    }
}
