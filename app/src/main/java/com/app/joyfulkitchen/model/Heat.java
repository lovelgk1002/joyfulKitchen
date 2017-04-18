package com.app.joyfulkitchen.model;

/**
 * 热量表
 * Created by Administrator on 2017/4/10.
 */

public class Heat {

    private int id; //编号
    private int userInfoId;
    /*private String breakfast;  //早餐
    private String lunch; //午餐
    private String dinner; //晚餐 dinner*/
    private double heatCount; //所需热量 heat_count

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public double getHeatCount() {
        return heatCount;
    }

    public void setHeatCount(double heatCount) {
        this.heatCount = heatCount;
    }
}
