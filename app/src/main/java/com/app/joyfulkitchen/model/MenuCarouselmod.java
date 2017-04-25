package com.app.joyfulkitchen.model;

/**
 * Created by Administrator on 2017/4/25.
 */
public class MenuCarouselmod {
    private int id;
    private String foodName;
    private String foodProtein;
    private String foodFat;
    private String foodCarbon;
    private String foodHeat;
    private String foodSalt;
    private String foodCalcium;
    private String foodPhosphorus;
    private String foodIron;
    private int foodType; //类型

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodProtein() {
        return foodProtein;
    }

    public void setFoodProtein(String foodProtein) {
        this.foodProtein = foodProtein;
    }

    public String getFoodFat() {
        return foodFat;
    }

    public void setFoodFat(String foodFat) {
        this.foodFat = foodFat;
    }

    public String getFoodCarbon() {
        return foodCarbon;
    }

    public void setFoodCarbon(String foodCarbon) {
        this.foodCarbon = foodCarbon;
    }

    public String getFoodHeat() {
        return foodHeat;
    }

    public void setFoodHeat(String foodHeat) {
        this.foodHeat = foodHeat;
    }

    public String getFoodSalt() {
        return foodSalt;
    }

    public void setFoodSalt(String foodSalt) {
        this.foodSalt = foodSalt;
    }

    public String getFoodCalcium() {
        return foodCalcium;
    }

    public void setFoodCalcium(String foodCalcium) {
        this.foodCalcium = foodCalcium;
    }

    public String getFoodPhosphorus() {
        return foodPhosphorus;
    }

    public void setFoodPhosphorus(String foodPhosphorus) {
        this.foodPhosphorus = foodPhosphorus;
    }

    public String getFoodIron() {
        return foodIron;
    }

    public void setFoodIron(String foodIron) {
        this.foodIron = foodIron;
    }

    public int getFoodType() {
        return foodType;
    }

    public void setFoodType(int foodType) {
        this.foodType = foodType;
    }

    @Override
    public String toString() {
        return "MenuCarouselmod{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", foodProtein='" + foodProtein + '\'' +
                ", foodFat='" + foodFat + '\'' +
                ", foodCarbon='" + foodCarbon + '\'' +
                ", foodHeat='" + foodHeat + '\'' +
                ", foodSalt='" + foodSalt + '\'' +
                ", foodCalcium='" + foodCalcium + '\'' +
                ", foodPhosphorus='" + foodPhosphorus + '\'' +
                ", foodIron='" + foodIron + '\'' +
                ", foodType=" + foodType +
                '}';
    }
}
