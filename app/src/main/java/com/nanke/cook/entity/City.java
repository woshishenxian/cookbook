package com.nanke.cook.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by vince on 16/11/22.
 */

@Entity
public class City {

    @Id
    private Long id;

    private String code;
    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1541214179)
    public City(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
    @Generated(hash = 750791287)
    public City() {
    }


}
