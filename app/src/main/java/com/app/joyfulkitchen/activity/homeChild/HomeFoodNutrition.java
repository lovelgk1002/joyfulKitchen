package com.app.joyfulkitchen.activity.homeChild;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.app.joyfulkitchen.activity.R;

/**
 * Created by Administrator on 2017/4/19.
 */

public class HomeFoodNutrition extends Activity {

    /*食物选择*/
    private String food=null;//当前称量的食物
    private TextView changetv ;//显示当前食物tv

    static  HomeChangeFood changefood;

    Bundle bl;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //获取到上一个页面传过来的Intent
        intent=this.getIntent();
        //获取Intent中的Bundle数据
        bl=intent.getExtras();



        String food = bl.getString("food");

        setContentView(R.layout.home_food_nutrition);
        showView();

        changetv.setText(setChangetv(food));
    }

    public  void showView(){
        changetv =(TextView)findViewById(R.id.home_change_tv);//控件textView显示当前称量的食物
    }


    /**
     * 设置显示当前的食物
     * */
    public String setChangetv(String food){
        if(food == null || food.equals("")){
            return "你还没选择食物";
        }else {
            return "当前称量的食物是"+food;
        }
    }
}
