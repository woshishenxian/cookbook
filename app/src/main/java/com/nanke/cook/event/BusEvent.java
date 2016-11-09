package com.nanke.cook.event;

/**
 * Created by vince on 16/11/9.
 */

public class BusEvent {
    public static final int TYPE_CHANGE_THEME = 1;

    private int type;

    public BusEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
