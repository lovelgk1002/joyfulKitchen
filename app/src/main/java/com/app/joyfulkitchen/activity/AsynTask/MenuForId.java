package com.app.joyfulkitchen.activity.AsynTask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.BaseAdapter;

import com.app.joyfulkitchen.model.Message;
import com.app.joyfulkitchen.util.MenuAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */
public class MenuForId extends AsyncTask{
    private BaseAdapter adapter;
    private int id;
    private List<Message> menuInfos;
    private String title;
    private String imtro;
    private String img;
    private String step;
    private Bitmap menuForBit;
    private String result;
    private String ingredients;
    @Override
    protected Object doInBackground(Object[] params) {
        id = (int) params[0];
        menuInfos = (List<Message>) params[1];
        adapter = (BaseAdapter) params[2];

        result = MenuAPI.getRequest4(id);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray datas = jsonObject.getJSONObject("result").getJSONArray("data");
          for(int i=0;i<datas.length();i++){
              JSONObject tree = datas.optJSONObject(i);
              /*食物名*/
              title = tree.getString("title");
              /*描述*/
              imtro = tree.getString("imtro");

              ingredients = tree.getString("ingredients");
              Message m = new Message();
              m.setIngredients(ingredients);
              menuInfos.add(m);


          }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return title;
    }
    //做完后执行
    @Override
    protected void onPostExecute(Object result) {

        adapter.notifyDataSetChanged();
    }
}
