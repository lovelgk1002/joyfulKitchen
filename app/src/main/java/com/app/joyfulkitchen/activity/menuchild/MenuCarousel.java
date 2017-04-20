package com.app.joyfulkitchen.activity.menuchild;
/*
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
*/

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
/*import android.widget.GridView;
import android.widget.SimpleAdapter;*/
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.app.joyfulkitchen.activity.R;
import com.app.joyfulkitchen.model.MenuCarouselType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuCarousel extends Activity {
    private  String toolsList[];
    private TextView toolsTextViews[];
    private  TextView textView;
    private View views[];
    private LayoutInflater inflater;
    private ScrollView scrollView;
    private FragmentManager manager;
    private FragmentTransaction transaction;


    private MenuCarouselType menuCarouselType = new MenuCarouselType();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_classify);


        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        menuCarouselType.setFoodType(0); //自定义默认传值
        getIntent().putExtra("TYPE",menuCarouselType.toString());//得到数值

        transaction.add(R.id.framelayout_fr, new Carousel(), "肉类");//默认显示这个Fragment
        transaction.commit();//提交
        inflater = LayoutInflater.from(MenuCarousel.this.getApplicationContext());
        scrollView = (ScrollView) MenuCarousel.this.findViewById(R.id.tools_scrlllview);
        showToolsView();
    }
    //
//        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView text = (TextView) view.findViewById(R.id.text);
//                String str =  text.getText().toString();
//                Toast.makeText(MainActivity.this, ""+str, Toast.LENGTH_SHORT).show();
//            }
//        });


    /*

        /*动态生成items中的textView*/
    private  void showToolsView(){
        toolsList = new String[]{"谷物","豆类","根茎类","叶菜类","菌类","海菜类","茄瓜类","干果类",
                "飞禽类","乳类","蛋类","鱼类"};
        LinearLayout toolsLayout  = (LinearLayout) MenuCarousel.this.findViewById(R.id.tools);
        toolsTextViews = new TextView[toolsList.length];
        views = new View[toolsList.length];
        for (int i=0;i<toolsList.length;i++){
            View view = inflater.inflate(R.layout.food_classify_item,null);

            view.setId(i);
            view.setOnClickListener(toolsItemListener);
            textView = (TextView)view.findViewById(R.id.classify_text);
            //textView.
            // textView.setBackgroundResource(R.drawable.classify_textview_border02);

            textView.setText(toolsList[i]);
            toolsLayout.addView(view);
            toolsTextViews[i] = textView;
            views[i] = view;
        }
        changeTextColor(0);

    }


    private View.OnClickListener toolsItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int n = v.getId();
            changeTextColor(n);

            switch (n){
                case 0:
                    menuCarouselType.setFoodType(0);
                    getIntent().putExtra("TYPE",menuCarouselType.toString());
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr, new Carousel(),"谷物");
                    transaction.commit();
                    /// Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    menuCarouselType.setFoodType(1);
                    getIntent().putExtra("TYPE",menuCarouselType.toString());
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr,new Carousel(),"豆类");
                    transaction.commit();
                    // Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    menuCarouselType.setFoodType(2);
                    getIntent().putExtra("TYPE",menuCarouselType.toString());
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr,new Carousel(),"根茎类");
                    transaction.commit();
                    // Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    menuCarouselType.setFoodType(3);
                    getIntent().putExtra("TYPE",menuCarouselType.toString());
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr,new Carousel(),"叶菜类");
                    transaction.commit();
                    // Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    menuCarouselType.setFoodType(4);
                    getIntent().putExtra("TYPE",menuCarouselType.toString());
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr,new Carousel(),"菌类");
                    transaction.commit();
                    //Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    menuCarouselType.setFoodType(5);
                    getIntent().putExtra("TYPE",menuCarouselType.toString());
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr,new Carousel(),"海菜类");
                    transaction.commit();
                    // Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
                case 6:
                    menuCarouselType.setFoodType(6);
                    getIntent().putExtra("TYPE",menuCarouselType.toString());
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr,new Carousel(),"茄瓜类");
                    transaction.commit();
                    // Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
                case 7:
                    menuCarouselType.setFoodType(7);
                    getIntent().putExtra("TYPE",menuCarouselType.toString());
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr,new Carousel(),"干果类");
                    transaction.commit();
                    // Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
                case 8:
                    menuCarouselType.setFoodType(8);
                    getIntent().putExtra("TYPE",menuCarouselType.toString());
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr,new Carousel(),"飞禽类");
                    transaction.commit();
                    // Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
                case 9:
                    menuCarouselType.setFoodType(9);
                    getIntent().putExtra("TYPE",menuCarouselType.toString());
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr,new Carousel(),"乳类");
                    transaction.commit();
                    // Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
                case 10:
                    menuCarouselType.setFoodType(10);
                    getIntent().putExtra("TYPE",menuCarouselType.toString());
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr,new Carousel(),"蛋类");
                    transaction.commit();
                    // Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
                case 11:
                    menuCarouselType.setFoodType(11);
                    getIntent().putExtra("TYPE",menuCarouselType.toString());
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr,new Carousel(),"鱼类");
                    transaction.commit();
                    //Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    /*  改变textView的颜色*/
    private void changeTextColor(int id) {
        for (int i = 0; i < toolsTextViews.length; i++) {
            if (i != id) {
                toolsTextViews[i].setTextColor(0xff000000);
                toolsTextViews[i].setBackgroundResource(R.drawable.classify_textview_border02);
            }
        }
        toolsTextViews[id].setBackgroundResource(android.R.color.white);
        toolsTextViews[id].setTextColor(0xffff5d5e);
    }
}

