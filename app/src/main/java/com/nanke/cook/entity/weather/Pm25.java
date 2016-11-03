package com.nanke.cook.entity.weather;

/**
 * Created by vince on 16/11/3.
 */

public class Pm25 {
    private String key;
    private String show_desc;
    private String dateTime;
    private String cityName;
    private Pm25_Pm25 pm25;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getShow_desc() {
        return show_desc;
    }

    public void setShow_desc(String show_desc) {
        this.show_desc = show_desc;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Pm25_Pm25 getPm25() {
        return pm25;
    }

    public void setPm25(Pm25_Pm25 pm25) {
        this.pm25 = pm25;
    }
}
