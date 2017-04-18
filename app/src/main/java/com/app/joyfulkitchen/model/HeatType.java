package com.app.joyfulkitchen.model;

/**
 * Created by Administrator on 2017/4/10.
 */

public class HeatType {

    private int id ;
    private String breakfast ;
    private String lunch ;
    private String dinner ;
    private int heatId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public int getHeatId() {
        return heatId;
    }

    public void setHeatId(int heatId) {
        this.heatId = heatId;
    }
}
