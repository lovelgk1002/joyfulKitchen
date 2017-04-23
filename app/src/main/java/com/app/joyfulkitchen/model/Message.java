package com.app.joyfulkitchen.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/5.
 */
public class Message implements Serializable{
    private int id;

    private Bitmap img;

    private String menuName;

    private String burden;
    private String step;

    private String imtro;
    /*主料*/
    private String ingredients;

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getBurden() {
        return burden;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }

    public String getImtro() {
        return imtro;
    }

    public void setImtro(String imtro) {
        this.imtro = imtro;
    }

    public Message(int id, Bitmap img, String menuName, String burden, String step, String imtro, String ingredients) {
        this.id = id;
        this.img = img;
        this.menuName = menuName;
        this.burden = burden;
        this.step = step;
        this.imtro = imtro;
        this.ingredients = ingredients;
    }

    public Message() {
    }
}
