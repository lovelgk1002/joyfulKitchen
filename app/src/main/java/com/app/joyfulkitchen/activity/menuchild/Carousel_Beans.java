package com.app.joyfulkitchen.activity.menuchild;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.app.joyfulkitchen.activity.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/17.
 */
public class Carousel_Beans extends Fragment {

    private GridView gview;
    private List<Map<String,Object>> data_list;
    private SimpleAdapter simpleAdapter;
    private TextView textView;

    private String[] text ={"黑豆","芸豆","豌豆","大豆","红豆","土豆","小刀豆","菜豆","红小豆","青豆"};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_classify_carousel_f2,container,false);

        //找到GridView
        gview = (GridView) view.findViewById(R.id.carousel_f1_grid);
        gview.setSelector(new ColorDrawable(Color.TRANSPARENT));//去掉点击效果（灰色背景）

        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String[] from ={"text"};
        int[] to = {R.id.carousel_f1_text};
        simpleAdapter = new SimpleAdapter(getActivity(),data_list, R.layout.menu_centre,from,to);
        gview.setAdapter(simpleAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public List<Map<String,Object>> getData(){

        for (int i=0;i<text.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("text",text[i]);
            data_list.add(map);
        }
        return  data_list;
    }
}
