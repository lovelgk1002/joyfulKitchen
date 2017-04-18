package com.app.joyfulkitchen.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/10.
 */

public class JoyfulKitChenOpenHelper extends SQLiteOpenHelper {

    /**
     * Users表创建语句
     */
    public static final String CREATE_USERS = "create table Users ("+
            "id integer primary key autoincrement,"+
            "email text,"+
            "password text," +
            "createTime integer)";
    /**
     * UserInfo表创建语句
     */
    public static final String CREATE_USER_INFO = "create table UserInfo(" +
            "id integer primary key autoincrement," +
            "nickName text," +
            "user_photo text," +
            "height integer," +
            "weight integer," +
            "age integer," +
            "sex text," +
            "target text," +
            "working_strength text," +
            "heat_id integer," +
            "userInfoUser integer)";
    /**
     * Heat表的创建语句
     */
    public static final String CREATE_HEAT = "create table Heat(" +
            "id integer primary key autoincrement," +
            "user_info_id integer," +
            "heat_count double,)";
    /**
     * HeatType表的创建语句
     */
    public static final String CREATE_HEAT_TYPE = "create table HeatType(" +
            "id integer primary key autoincrement," +
            "breakfast text," +
            "lunch text," +
            "dinner text," +
            "heat_id integer)";

    public JoyfulKitChenOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USERS);//创建Users表
        db.execSQL(CREATE_USER_INFO);//创建UserInfo表
        db.execSQL(CREATE_HEAT);//创建Heat表
        db.execSQL(CREATE_HEAT_TYPE);//创建HeatType表

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
