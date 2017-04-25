package com.app.joyfulkitchen.activity.menuchild;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.app.joyfulkitchen.activity.R;
import com.app.joyfulkitchen.activity.homeChild.HomeFoodNutrition;
import com.app.joyfulkitchen.db.JoyfulKitDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/12.
 */
public class Carousel extends Fragment {

    private GridView gview;
    private List<Map<String,Object>>  data_list;
    private SimpleAdapter simpleAdapter;
    private TextView textView;
    //数据库
    private JoyfulKitDB joyfulKitDB;
    private SQLiteDatabase sdb;
    private Cursor cursor;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_classify_carousel_f1,container,false);

        joyfulKitDB = new JoyfulKitDB(getActivity());
        sdb = joyfulKitDB.getReadableDatabase();

        String type = getActivity().getIntent().getStringExtra("TYPE");
        //按条件查询
        cursor = sdb.query("Nutrition", null, "foodType="+type, null, null, null, null);

        //找到GridView
        gview = (GridView) view.findViewById(R.id.carousel_f1_grid);
        gview.setSelector(new ColorDrawable(Color.TRANSPARENT));//去掉点击效果（灰色背景）

        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String[] from ={"foodName","id"};
        int[] to = {R.id.carousel_f1_text,R.id.Nutrition_id};
        simpleAdapter = new SimpleAdapter(getActivity(),data_list, R.layout.menu_centre,from,to);
        gview.setAdapter(simpleAdapter);

        //点击获取gview子类
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView = (TextView) view.findViewById(R.id.carousel_f1_text); //点击得到该分类的子类名称
                TextView textView2 = (TextView) view.findViewById(R.id.Nutrition_id);//这控件为隐藏状态 却是储存Nutrition表的id
                Intent intent=new Intent();
                intent.setClass(getActivity(), HomeFoodNutrition.class);
                intent.putExtra("food",textView.getText().toString() );
                startActivityForResult(intent, 0);
            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public List<Map<String,Object>> getData(){
        int count = cursor.getCount();

        for (int i=0;i<count;i++){
            cursor.moveToNext();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("foodName",cursor.getString(cursor.getColumnIndex("foodName")));//得到名称
            map.put("id",cursor.getInt(cursor.getColumnIndex("id")));//得到id
            data_list.add(map);
        }
        return  data_list;
    }

}
