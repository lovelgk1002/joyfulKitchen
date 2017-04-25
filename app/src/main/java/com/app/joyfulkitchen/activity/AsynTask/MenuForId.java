package com.app.joyfulkitchen.activity.AsynTask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.joyfulkitchen.activity.R;
import com.app.joyfulkitchen.model.Menustep;
import com.app.joyfulkitchen.model.Message;
import com.app.joyfulkitchen.util.MenuAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/20.
 */
public class MenuForId extends AsyncTask{
    private BaseAdapter adapter;
    private String id;
    private List<Message> menuInfos;
    /*  private String title;
      private String imtro;
      private String img;
      private String step;
      private String ingredients;
      private String burden;*/
    private Bitmap menuForBit;
    private String result;
    private List<Menustep> menusteps;
    private  Message m;
    private JSONArray steps;
    @Override
    protected Object doInBackground(Object[] params) {
        id = (String) params[0];
        menuInfos = (List<Message>) params[1];
        adapter = (BaseAdapter) params[2];
        menusteps = (List<Menustep>) params[3];
        result = MenuAPI.getRequest4(id);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray datas = jsonObject.getJSONObject("result").getJSONArray("data");
            for(int i=0;i<datas.length();i++){
                JSONObject tree = datas.optJSONObject(i);
              /*食物名*/
                String  title = tree.getString("title");

              /*描述*/
                String imtro = tree.getString("imtro");
              /*主食材*/
                String ingredients = tree.getString("ingredients");

                /*主图*/
                JSONArray album = tree.optJSONArray("albums");
                String image = album.getString(0);
                Bitmap mainImg = MenuAPI.getBitmap(image);
              /*配料*/
                String burden = tree.getString("burden");
                m = new Message();
                m.setImtro(imtro);
                m.setBurden(burden);
                m.setMenuName(title);
                m.setIngredients(ingredients);
                m.setImg(mainImg);
                m.setSteps(steps);
                menuInfos.add(m);
              /*做法*/
                steps = tree.getJSONArray("steps");//菜谱步骤
                for (int j = 0; j < steps.length(); j++) {
                    JSONObject ste = steps.optJSONObject(j);
                    String img = ste.getString("img");

                    Bitmap mainImg1 = MenuAPI.getBitmap(img);
                    String step = ste.getString("step");
                    Menustep ms = new Menustep();
                    ms.setImg(mainImg1);
                    ms.setDesccrible(step);
                    menusteps.add(ms);
                    //list.add(map);
                }




            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return m;
    }
    //做完后执行
    @Override
    protected void onPostExecute(Object result) {

        adapter.notifyDataSetChanged();
    }
}
