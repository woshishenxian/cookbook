package com.nanke.cook.source;

import java.util.List;

/**
 * Created by vince on 16/11/3.
 */

public interface ObjCallBack<T>{
    void onTasksLoaded(T tasks);

    void onDataNotAvailable(String msg);

    void start();

    void onComplete();
}
