package com.app.joyfulkitchen.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.joyfulkitchen.model.Heat;
import com.app.joyfulkitchen.model.HeatType;
import com.app.joyfulkitchen.model.UserInfo;
import com.app.joyfulkitchen.model.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */

public class JoyfulKitChenDB {



     /*
     第一个参数String：表名
     第二个参数String[]:要查询的列名
     第三个参数String：查询条件
     第四个参数String[]：查询条件的参数
     第五个参数String:对查询的结果进行分组
     第六个参数String：对分组的结果进行限制
     第七个参数String：对查询的结果进行排序
     String[] columns = new String[] { "id", "username", "info" };
     String selection = "id=?";
     String[] selectionArgs = { String.valueOf(id) };
     String groupBy = null;
     String having = null;
     String orderBy = null;
    */

    /**
     * 数据库名称
     */
    public static final String DB_NAME = "joyful_kit";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static JoyfulKitChenDB joyfulKitChenDB;

    private SQLiteDatabase db;

    /**
     * 创建私有构造方法
     */
    private JoyfulKitChenDB(Context context){
        JoyfulKitChenOpenHelper dbHelper = new JoyfulKitChenOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }
    /**
     * 获取JoyfulKitChenDB的实例
     */
    public synchronized static JoyfulKitChenDB getInstance(Context context){
        if (joyfulKitChenDB == null){
            joyfulKitChenDB = new JoyfulKitChenDB(context);
        }
        return joyfulKitChenDB;
    }

    /**
     * 把Users实例储存到数据库
     */
    public void saveUsers(Users users){

        if (users != null){
            ContentValues values = new ContentValues();
            values.put("email",users.getEmail());
            values.put("password",users.getPassword());
            values.put("createTime",users.getCreateTime());
            db.insert("Users",null,values);
        }
    }

    /**
     * 从数据库读取Users表的信息
     */
    public List<Users> loadUsers(){
        List<Users> list = new ArrayList<>();

        Cursor cursor = db.query("Users",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Users users = new Users();
                users.setId(cursor.getInt(cursor.getColumnIndex("id")));
                users.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                users.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                users.setCreateTime(cursor.getLong(cursor.getColumnIndex("createTime")));
                list.add(users);
            }while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 把UserInfo实例储存到数据库
     * @param userInfo
     */
    public void saveUserInfo(UserInfo userInfo){
        if (userInfo != null){
            ContentValues values = new ContentValues();
            values.put("user_photo",userInfo.getUserPhoto());
            values.put("height",userInfo.getHeight());
            values.put("weight",userInfo.getWeight());
            values.put("age",userInfo.getAge());
            values.put("sex",userInfo.getSex());
            values.put("nickName",userInfo.getNickName());
            values.put("target",userInfo.getTarget());
            values.put("working_strength",userInfo.getWorkingStrength());
            values.put("heat_id",userInfo.getHeatId());
            db.insert("UserInfo",null,values);
        }
    }

    /**
     * 从数据库读取UserInfo表的信息
     * @param
     * @return
     */
    public List<UserInfo> load(int userInfoUser){
        List<UserInfo> list = new ArrayList<>();
        Cursor cursor = db.query("UserInfo",null,"userInfoUser=?",new String[]{String.valueOf(userInfoUser)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                UserInfo userInfo = new UserInfo();
                userInfo.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                userInfo.setHeight(cursor.getInt(cursor.getColumnIndex("height")));
                userInfo.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));
                userInfo.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                userInfo.setTarget(cursor.getString(cursor.getColumnIndex("target")));
                userInfo.setUserPhoto(cursor.getString(cursor.getColumnIndex("user_photo")));
                userInfo.setWeight(cursor.getInt(cursor.getColumnIndex("weight")));
                userInfo.setWorkingStrength(cursor.getString(cursor.getColumnIndex("working_strength")));
                userInfo.setHeatId(cursor.getInt(cursor.getColumnIndex("heat_id")));
                userInfo.setUserInfoUser(userInfoUser);
                list.add(userInfo);
            }while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 把Heat实例储存到数据库
     * @param heat
     */
    public void saveHeat(Heat heat){
        if (heat != null){
            ContentValues values = new ContentValues();
            values.put("user_info_id",heat.getUserInfoId());
            values.put("heat_count",heat.getHeatCount());
            db.insert("Heat",null,values);
        }
    }

    /**
     * 从数据库读取heat表的信息
     * @param userInfoId
     * @return
     */
    public List<Heat> loadHeat(int userInfoId){
        List<Heat> list = new ArrayList<>();
        Cursor cursor = db.query("Heat",null,"user_info_id=?",new String[]{String.valueOf(userInfoId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                Heat heat = new Heat();
                heat.setUserInfoId(userInfoId);
                heat.setHeatCount(cursor.getDouble(cursor.getColumnIndex("heat_count")));
                list.add(heat);
            }while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 把HeatType实例储存到数据库
     * @param heatType
     */
    public void saveHeatType(HeatType heatType){

        if (heatType != null){
            ContentValues values = new ContentValues();
            values.put("breakfast",heatType.getBreakfast());
            values.put("lunch",heatType.getLunch());
            values.put("dinner",heatType.getDinner());
            values.put("heat_id",heatType.getHeatId());
            db.insert("HeatType",null,values);
        }
    }

    /**
     * 从数据库读取HeatType表的信息
     * @return
     */
    public List<HeatType> loadHeatType(int heatId){

        List<HeatType> list = new ArrayList<>();
        Cursor cursor = db.query("HeatType",null,"heat_id=?",new String[]{String.valueOf(heatId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                HeatType heatType = new HeatType();
                heatType.setBreakfast(cursor.getString(cursor.getColumnIndex("breakfast")));
                heatType.setLunch(cursor.getString(cursor.getColumnIndex("lunch")));
                heatType.setDinner(cursor.getString(cursor.getColumnIndex("dinner")));
                heatType.setHeatId(heatId);
                list.add(heatType);
            }while (cursor.moveToNext());
        }
        return list;
    }



}
