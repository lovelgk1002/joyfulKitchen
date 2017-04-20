package com.app.joyfulkitchen.activity.homeChild;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.joyfulkitchen.activity.R;


/**
 * Created by Administrator on 2017/4/19.
 */

public class HomeChangeFood extends Activity {



    /*****************顶部搜索框参数*******************/

    /*顶部搜索框变量*/
    private LinearLayout home_edt_ly;
    private LinearLayout home_tv_ly;
    private EditText home_search ;//搜索框
    private ImageView home_title_set;//搜索按钮


    /******************顶部搜索框参数结束***************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_change_food);
        showView();
        //食物切换显示

		//search_btn点击获取home_search的值*/
        home_title_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(HomeChangeFood.this, HomeFoodNutrition.class);
                intent.putExtra("food",home_search.getText().toString() );
                startActivityForResult(intent, 0);

            }
        });


        //changetv.setText(setChangetv(food));//设置显示*/
    }

    public void showView(){

        home_tv_ly  =(LinearLayout) findViewById(R.id.home_tv_ly);//title文本框设置布局
        home_edt_ly =(LinearLayout)findViewById(R.id.home_edt_ly);//title文本设置布局
        //home_edt_ly.setVisibility(View.GONE);//不可见

        home_search = (EditText) findViewById(R.id.home_search_edt);//控件EditText搜索当前称量的食物
        home_title_set = (ImageView)findViewById(R.id.home_title_set);//控件ImageView设置当前称量的食物


    }





   /* *//**
     * 按钮点击事件
     *//*
    class HomeClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.home_title_set:
                    String search_name = home_search.getText().toString();
                    food = search_name ;
                    changetv.setText(setChangetv(food));//设置显示
                    break;
				*//*case R.id.home_search_img:
					home_edt_ly.setVisibility(View.VISIBLE);
					home_tv_ly.setVisibility(View.GONE);
					break;*//*
                default:
                    break;
            }
        }
    }*/

}
