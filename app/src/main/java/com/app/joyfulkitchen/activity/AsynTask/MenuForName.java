package com.app.joyfulkitchen.activity.AsynTask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.BaseAdapter;

import com.app.joyfulkitchen.model.Message;
import com.app.joyfulkitchen.util.MenuAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;


/**
 * 根据菜名搜索菜谱
 */
public class MenuForName extends AsyncTask{
    //JSON字符串
    private String result;
    private String menuName;
    private JSONObject menuInfo;
    private BaseAdapter adapter;
    private String title;
    private String image;
    private List<Message> menuList;
    private Bitmap mainImg;
    private int id;
    private String imtro;

    @Override
    protected Object doInBackground(Object[] params) {

        menuName = (String) params[0];
        adapter = (BaseAdapter) params[1];
        menuList = (List<Message>) params[2];

        result = MenuAPI.getRequest1(menuName);

        try {
            JSONObject jsonObject = new JSONObject(result);
            menuInfo = jsonObject.getJSONObject("result");
            JSONArray datas = menuInfo.getJSONArray("data");

            for(int i=0;i<datas.length();i++){
                JSONObject  data= datas.optJSONObject(i);
                /*菜名*/
                title = data.getString("title");
                /*菜谱Id*/
                id = data.getInt("id");
                /*获取图片*/
                JSONArray album = data.optJSONArray("albums");
                image = album.getString(0);
                mainImg = MenuAPI.getBitmap(image);

                imtro = data.getString("imtro");

                Message m = new Message();
                m.setId(id);
                m.setMenuName(title);
                m.setImg(mainImg);
                m.setImtro(imtro);
                menuList.add(m);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
