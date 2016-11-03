package com.nanke.cook.source;

import java.util.List;

/**
 * Created by vince on 16/11/3.
 */

public interface ArrCallBack<T>{
    void onTasksLoaded(List<T> tasks);

    void onDataNotAvailable(String msg);

    void start();

    void onComplete();
}
