package com.nanke.cook.entity.weather;

/**
 * Created by vince on 16/11/3.
 */

public class Realtime_Weather {

    // 当日天气

    private String temperature;
    private String humidity;
    private String info;
    private String img;

    public String getTemperature() {
        return temperature + "°";
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return "湿度\n"+ humidity+"%";
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

    public String getImg() {
        return img;
    }
    public int getIndex() {
        int index = 0;
        try {
            index = Integer.valueOf(img);
        }catch (Exception e){

        }
        return index;
    }

    public void setImg(String img) {
        this.img = img;
    }



    //未来5天
}
