package com.nanke.cook.entity;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class FoodsData {
    private Integer total;
    private List<Food> tngou;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Food> getTngou() {
        return tngou;
    }

    public void setTngou(List<Food> tngou) {
        this.tngou = tngou;
    }
}
