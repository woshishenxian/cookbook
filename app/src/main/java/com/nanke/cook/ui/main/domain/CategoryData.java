package com.nanke.cook.ui.main.domain;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class CategoryData {
    private boolean status;
    private List<Category> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Category> getTngou() {
        return tngou;
    }

    public void setTngou(List<Category> tngou) {
        this.tngou = tngou;
    }
}
