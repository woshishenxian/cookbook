package com.nanke.cook.entity.weather;

import java.util.List;

/**
 * Created by vince on 16/11/3.
 */

public class WeatherData {


    private int error_code;//返回码 0 成功
    private String reason;//返回说明
    private Result result;//返回结果集


    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }





}

