package com.app.joyfulkitchen.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText login_username;
    private EditText login_password;
    private Button user_login_button ,user_register_button;
    private CheckBox cb1,cb2;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_username=(EditText)findViewById(R.id.login_username);
        login_password=(EditText)findViewById(R.id.login_password);
        user_login_button=(Button)findViewById(R.id.user_login_button);
        user_register_button = (Button) findViewById(R.id.user_register_button);
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);


        login_username.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(!isEmail(login_username.getText().toString().trim())){
                        Toast.makeText(LoginActivity.this, "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        login_password.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(login_password.getText().toString().trim().length()<8){
                        Toast.makeText(LoginActivity.this, "密码不能小于8个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });





        //判断记住密码多选框的状态
        if(sp.getBoolean("ISCHECK", false))
        {
            //设置默认是记录密码状态
            cb1.setChecked(false);
            login_username.setText(sp.getString("USER_NAME", ""));
            login_password.setText(sp.getString("PASSWORD", ""));
            //判断自动登陆多选框状态
            if(sp.getBoolean("AUTO_ISCHECK", false))
            {
                //设置默认是自动登录状态
                cb2.setChecked(false);
                //跳转界面
                Intent intent = new Intent(LoginActivity.this,LogoActivity.class);
                startActivity(intent);
            }
        }


        user_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        user_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

       //监听记住密码多选框按钮事件
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb1.isChecked()) {

                    Log.i("--------------","记住密码已选中");
                    sp.edit().putBoolean("ISCHECK", true).commit();

                }else {

                    Log.i("--------------","记住密码没有选中");
                    sp.edit().putBoolean("ISCHECK", false).commit();

                }

            }
        });

        //监听自动登录多选框事件
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb2.isChecked()) {
                    Log.i("--------------","自动登录已选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

                } else {
                    Log.i("--------------","自动登录没有选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
            }
        });

    }

    private void login(){

        final String email = login_username.getText().toString().trim();
        final String pwd = login_password.getText().toString().trim();

        String path = "http://192.168.191.1:8088/login";

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormEncodingBuilder()
                .add("email", email)
                .add("password", pwd)
                .build();

        Request request = new Request.Builder()
                .url(path)
                .post(formBody)
                .build();


        Call caller = client.newCall(request);
        caller.enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (checkEdit()) {
                    String str = response.body().string();
                    Log.i("-------name--------:", str);
                    if (str.equals("true")) {
                        //登录成功和记住密码框为选中状态才保存用户信息
                        if (cb1.isChecked()) {
                            //记住用户名、密码、
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("USER_NAME", email);
                            editor.putString("PASSWORD", pwd);
                            editor.commit();
                        }
                        Intent intent = new Intent(LoginActivity.this, LogoActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    private boolean checkEdit(){
        if(login_username.getText().toString().trim().equals("")){
            Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }else if(login_password.getText().toString().trim().equals("")){
            Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
        return false;
    }

    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
