package com.nanke.cook.db;

import com.nanke.cook.application.App;
import com.nanke.cook.entity.DaoMaster;
import com.nanke.cook.entity.DaoSession;
import com.nanke.cook.entity.Food;
import com.nanke.cook.entity.FoodDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by vince on 16/10/27.
 */

public class DBManager {
    private final static String dbName = "collect_db";
    private static DBManager mInstance;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private int pageSize = 10;


    public DBManager() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(App.getContext(), dbName, null);
        daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    /**
     * 获取单例引用
     *
     * @return
     */
    public static DBManager getInstance() {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager();
                }
            }
        }
        return mInstance;
    }


    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public void setDaoMaster(DaoMaster daoMaster) {
        this.daoMaster = daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public void insertFood(Food food) {
        food.setIsCollected(true);
        List<Food> foods = daoSession.getFoodDao().queryBuilder().where(FoodDao.Properties.Id.eq(food.getId())).list();
        if( foods.size()>0){
            daoSession.getFoodDao().deleteInTx(foods);
        }
        daoSession.getFoodDao().insert(food);
    }

    public void deleteFood(Food food) {
        daoSession.getFoodDao().delete(food);
    }

    public List<Food> queryFoodsByPage(int page) {
        if (page == 0)
            return new ArrayList<>();
        QueryBuilder queryBuilder = daoSession.getFoodDao().queryBuilder().orderDesc(FoodDao.Properties.FoodId).limit(pageSize);
        if(page == 1){
            return queryBuilder.list();
        }
        return queryBuilder.offset((page - 1) * pageSize).list();

    }

    public void clearFoods() {
        daoSession.getFoodDao().deleteAll();

    }
}
