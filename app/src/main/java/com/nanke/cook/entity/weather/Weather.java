package com.nanke.cook.entity.weather;

/**
 * Created by vince on 16/11/3.
 */

public class Weather {
    private String date;
    private Weather_Info info;
    private String weak;
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

    public String getWeak() {
        return weak;
    }

    public void setWeak(String weak) {
        this.weak = weak;
    }

    public String getNongli() {
        return nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli;
    }
}
