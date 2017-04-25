package com.app.joyfulkitchen.activity.menuchild;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.app.joyfulkitchen.activity.AsynTask.MenuForName;
import com.app.joyfulkitchen.activity.R;
import com.app.joyfulkitchen.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */
public class Newest extends Activity{

    private ListView lv_newest;
    private BaseAdapter adapter;
    private String menuName; /*搜索食物名*/
    private List<Message> menuList = new ArrayList<Message>();
    private Message menu;
   /* private Bitmap menuForImage;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_newest);

        adapter =new BaseAdapter() {
            @Override
            public int getCount() {
                return menuList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = null;
                if(convertView==null){
                    Log.i("info:", "没有缓存，重新生成"+position);
                    LayoutInflater inflater = Newest.this.getLayoutInflater();
                    //因为getView()返回的对象，adapter会自动赋给ListView
                    view = inflater.inflate(R.layout.menu_item_newest, null);
                }else{
                    Log.i("info:", "有缓存，不需要重新生成"+position);
                    view = convertView;
                }
                Message m = menuList.get(position);

                /*设置listview菜名*/
                TextView t = (TextView) view.findViewById(R.id.list_i_txt);
                t.setText(m.getMenuName());
                 /*设置listview菜主题图片*/
                ImageView mImage = (ImageView) view.findViewById(R.id.menuIamge);
                mImage.setImageBitmap(m.getImg());

                return view;
            }

        };

        lv_newest = (ListView)findViewById(R.id.newsList);
        lv_newest.setAdapter(adapter);

        Bundle bundle = this.getIntent().getExtras();
         /*获取搜索栏的食物名*/
        menuName = bundle.getString("menuName").toString();
        MenuForName tasksMN = new MenuForName();
        tasksMN.execute(menuName,adapter,menuList);

        lv_newest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Bundle bundle=new Bundle();
                Message mm = menuList.get(position);
                //根据点击Item  传id；
                bundle.putString("id",mm.getId());
                Intent intent = new Intent(Newest.this,MenuInformation.class);
                intent.putExtras(bundle);
                finish();
                startActivity(intent);

            }
        });
    }
}
