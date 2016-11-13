package com.nanke.cook.entity.weather;

import com.nanke.cook.R;

/**
 * Created by vince on 16/11/3.
 */

public class Realtime_Weather {

    private int day[] = {R.mipmap.d_00,R.mipmap.d_01,R.mipmap.d_02,R.mipmap.d_03,R.mipmap.d_04,R.mipmap.d_05,R.mipmap.d_06,R.mipmap.d_07,
            R.mipmap.d_08,R.mipmap.d_09,R.mipmap.d_10,R.mipmap.d_11,R.mipmap.d_12,R.mipmap.d_13,R.mipmap.d_14,R.mipmap.d_15,R.mipmap.d_16,
            R.mipmap.d_17,R.mipmap.d_18,R.mipmap.d_19,R.mipmap.d_20,R.mipmap.d_21,R.mipmap.d_22,R.mipmap.d_23,R.mipmap.d_24,R.mipmap.d_25,
            R.mipmap.d_26,R.mipmap.d_27,R.mipmap.d_28,R.mipmap.d_29,R.mipmap.d_30,R.mipmap.d_31,R.mipmap.d_31,R.mipmap.d_53};

    // 当日天气

    private String temperature;
    private String humidity;
    private String info;
    private Integer img;

    public String getTemperature() {
        return temperature + "°";
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return "湿度"+ humidity+"%";
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getImg() {
        return img;
    }
    public int getImgRes() {
        return day[img];
    }

    public void setImg(Integer img) {
        this.img = img;
    }



    //未来5天
}
