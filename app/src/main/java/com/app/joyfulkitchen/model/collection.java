package com.app.joyfulkitchen.model;

/**
 * Created by Administrator on 2017/4/21.
 */
public class collection {
    private int id;
    /*api中的编号*/
    private int menuId;

    private float hot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public float getHot() {
        return hot;
    }

    public void setHot(float hot) {
        this.hot = hot;
    }

    public collection() {
       super();
    }
}

