package com.app.joyfulkitchen.model;

import android.graphics.Bitmap;

import org.json.JSONArray;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/5.
 */
public class Message implements Serializable{
    private String id;

    private Bitmap img;

    private String menuName;
    /*配料*/
    private String burden;

    /*菜谱描述*/
    private String imtro;
    /*主料*/
    private String ingredients;
    /*步骤*/
    private JSONArray steps;

    public String getId() {
        return id;
    }



    public void setId(String id) {
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

    public JSONArray getSteps() {
        return steps;
    }

    public void setSteps(JSONArray steps) {
        this.steps = steps;
    }

    public Message() {
    }
}
