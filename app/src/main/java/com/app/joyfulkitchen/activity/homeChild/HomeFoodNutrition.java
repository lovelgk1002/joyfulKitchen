package com.app.joyfulkitchen.activity.homeChild;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.app.joyfulkitchen.activity.R;
import com.app.joyfulkitchen.db.JoyfulKitDB;
import com.app.joyfulkitchen.model.MenuCarouselmod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */

public class HomeFoodNutrition extends Activity {

    /*食物选择*/
    private String food;//当前称量的食物
    private TextView changetv ;//显示当前食物tv

<<<<<<< HEAD
    private JoyfulKitDB joyfulKitDB;
    private SQLiteDatabase db;
    private Cursor cursor;
    private List<MenuCarouselmod> list = new ArrayList<>();
    private TextView foodProtein, foodFat,foodCarbon,foodSalt,kcal,
                      foodHeat,foodCalcium,foodPhosphorus,foodIron;


=======
>>>>>>> ee8c772c5de05fe9a6131785c51421547d394549

    Bundle bl;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_food_nutrition);
        showView();

        joyfulKitDB = new JoyfulKitDB(HomeFoodNutrition.this);
        db = joyfulKitDB.getReadableDatabase();


        //获取到上一个页面传过来的Intent
        intent=this.getIntent();
        //获取Intent中的Bundle数据
        bl=intent.getExtras();
         food = bl.getString("food");
        changetv.setText(setChangetv(food));

         qw();
    }

    public  void showView(){
        changetv =(TextView)findViewById(R.id.home_change_tv);//控件textView显示当前称量的食物
        kcal = (TextView) findViewById(R.id.Kcal);//大卡 显示
        foodProtein  = (TextView) findViewById(R.id.foodProtein);//蛋白质
        foodFat = (TextView) findViewById(R.id.foodFat);//脂肪
        foodCarbon = (TextView) findViewById(R.id.foodCarbon);//碳水化合物
        foodHeat = (TextView) findViewById(R.id.foodHeat);//热量
        foodSalt = (TextView) findViewById(R.id.foodSalt);//盐类
        foodCalcium = (TextView) findViewById(R.id.foodCalcium);//钙
        foodPhosphorus = (TextView) findViewById(R.id.foodPhosphorus);//磷
        foodIron = (TextView) findViewById(R.id.foodIron);//铁




    }


    /**
     * 设置显示当前 的食物
     * */
    public String setChangetv(String food){
        if(food == null || food.equals("")){
            return "你还没选择食物";
        }else {
            return ""+food;
<<<<<<< HEAD
        }
    }


    public int qw(){
        //Toast.makeText(HomeFoodNutrition.this, ""+food, Toast.LENGTH_SHORT).show();
        int size ;
        cursor = db.query("Nutrition", null,"foodName="+"'"+food+"'", null, null, null, null);

        int cout = cursor.getCount();
        MenuCarouselmod er = new MenuCarouselmod();
        for (int i=0;i<cout;i++){
            cursor.moveToNext();
            er.setFoodName(cursor.getString(cursor.getColumnIndex("foodName")));
            er.setFoodProtein(cursor.getString(cursor.getColumnIndex("foodProtein")));
            er.setFoodFat(cursor.getString(cursor.getColumnIndex("foodFat")));
            er.setFoodCarbon(cursor.getString(cursor.getColumnIndex("foodCarbon")));
            er.setFoodHeat(cursor.getString(cursor.getColumnIndex("foodHeat")));
            er.setFoodSalt(cursor.getString(cursor.getColumnIndex("foodSalt")));
            er.setFoodCalcium(cursor.getString(cursor.getColumnIndex("foodCalcium")));
            er.setFoodPhosphorus(cursor.getString(cursor.getColumnIndex("foodPhosphorus")));
            er.setFoodIron(cursor.getString(cursor.getColumnIndex("foodIron")));
            er.setFoodType(cursor.getInt(cursor.getColumnIndex("foodType")));
            list.add(er);
        }

        size = list.size();
        if (size<=0){
            Toast.makeText(HomeFoodNutrition.this, "该数据正在完善...", Toast.LENGTH_SHORT).show();
        }else {
            changetv.setText(list.get(0).getFoodName()+"g");
            foodProtein.setText(list.get(0).getFoodProtein()+"g");
            foodFat.setText(list.get(0).getFoodFat()+"g");
            foodCarbon.setText(list.get(0).getFoodCarbon()+"g");
            foodHeat.setText(list.get(0).getFoodHeat()+"kj");
            foodSalt.setText(list.get(0).getFoodSalt()+"ug");
            foodCalcium.setText(list.get(0).getFoodCalcium()+"ug");
            foodPhosphorus.setText(list.get(0).getFoodPhosphorus()+"ug");
            foodIron.setText(list.get(0).getFoodIron()+"ug");



=======
>>>>>>> ee8c772c5de05fe9a6131785c51421547d394549
        }
        cursor.close();
        db.close();
        return size;
    }

}
