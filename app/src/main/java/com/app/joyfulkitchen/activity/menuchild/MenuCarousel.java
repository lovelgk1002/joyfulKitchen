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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_classify);


        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.framelayout_fr, new Carousel(), "肉类");//默认显示这个
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
        toolsList = new String[]{"肉类","豆类","奶类","海鲜类","汤粥类","甜品","烘培","水果",
                "鸡蛋","私家菜"};
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
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr, new Carousel(),"肉类");
                    transaction.commit();
                    Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.framelayout_fr,new Carousel_Beans(),"豆类");
                    transaction.commit();
/*
                    Intent it = new Intent(MenuCarousel.this,Carousel_Beans.class);
                    Bundle bl = new Bundle();
                    bl.putInt("N",n);
                    it.putExtras(bl);
                    startActivity(it);*/


                    Toast.makeText(MenuCarousel.this,""+n, Toast.LENGTH_SHORT).show();
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

