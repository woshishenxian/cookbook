package com.nanke.cook.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by vince on 16/10/25.
 */


@Entity
public class Food {

    @Id
    private Long foodId;

    private int id;
    private String name;//
    private int infoclass;//分类
    private String img;//图片
    private String description;//描述
    private String keywords;//关键字
    private String message;//资讯内容
    private int count;//访问次数
    private int fcount;//收藏数
    private int rcount;//评论读数
    private String disease;//相关疾病
    private String food;//相关食物
    private String summary;//简介

    private boolean isCollected = false; //是否被收藏


    @Generated(hash = 1111400941)
    public Food(Long foodId, int id, String name, int infoclass, String img,
                String description, String keywords, String message, int count,
                int fcount, int rcount, String disease, String food, String summary,
                boolean isCollected) {
        this.foodId = foodId;
        this.id = id;
        this.name = name;
        this.infoclass = infoclass;
        this.img = img;
        this.description = description;
        this.keywords = keywords;
        this.message = message;
        this.count = count;
        this.fcount = fcount;
        this.rcount = rcount;
        this.disease = disease;
        this.food = food;
        this.summary = summary;
        this.isCollected = isCollected;
    }

    @Generated(hash = 866324199)
    public Food() {
    }


    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInfoclass() {
        return infoclass;
    }

    public void setInfoclass(int infoclass) {
        this.infoclass = infoclass;
    }

    public String getImg() {
        return img;
    }

    public String getImgUrl() {
        return "http://tnfs.tngou.net/img" + img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean getIsCollected() {
        return this.isCollected;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }


}
