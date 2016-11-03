package com.nanke.cook.entity.weather;

import java.util.List;

/**
 * Created by vince on 16/11/3.
 */

public class Weather_Info {
    private List<String> day;
    private List<String> night;
    private List<String> dawn;

    public List<String> getDawn() {
        return dawn;
    }

    public void setDawn(List<String> dawn) {
        this.dawn = dawn;
    }

    public List<String> getDay() {
        return day;
    }

    public void setDay(List<String> day) {
        this.day = day;
    }

    public List<String> getNight() {
        return night;
    }

    public void setNight(List<String> night) {
        this.night = night;
    }
}
