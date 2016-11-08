package com.nanke.cook.entity.weather;

import com.nanke.cook.R;

/**
 * Created by vince on 16/11/3.
 */

public class Weather {

    private int day[] = {R.mipmap.d_00,R.mipmap.d_01,R.mipmap.d_02,R.mipmap.d_03,R.mipmap.d_04,R.mipmap.d_05,R.mipmap.d_06,R.mipmap.d_07,
            R.mipmap.d_08,R.mipmap.d_09,R.mipmap.d_10,R.mipmap.d_11,R.mipmap.d_12,R.mipmap.d_13,R.mipmap.d_14,R.mipmap.d_15,R.mipmap.d_16,
            R.mipmap.d_17,R.mipmap.d_18,R.mipmap.d_19,R.mipmap.d_20,R.mipmap.d_21,R.mipmap.d_22,R.mipmap.d_23,R.mipmap.d_24,R.mipmap.d_25,
            R.mipmap.d_26,R.mipmap.d_27,R.mipmap.d_28,R.mipmap.d_29,R.mipmap.d_30,R.mipmap.d_31,R.mipmap.d_31,R.mipmap.d_53};


    private String date;
    private Weather_Info info;
    private String week;
    private String nongli;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Weather_Info getInfo() {
        return info;
    }

    public void setInfo(Weather_Info info) {
        this.info = info;
    }

    public String getWeek() {
        return "星期"+ week;
    }

    public int getWeatherImg(int index){
        return day[index];
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getNongli() {
        return nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli;
    }
}
