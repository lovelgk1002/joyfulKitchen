package com.app.joyfulkitchen.model;

/**
 * Created by Administrator on 2017/4/20.
 */
public class MenuCarouselType {
    private int foodType; //类型

    public int getFoodType() {
        return foodType;
    }

    public void setFoodType(int foodType) {
        this.foodType = foodType;
    }

    @Override
    public String toString() {
        return foodType+"";
    }
}
