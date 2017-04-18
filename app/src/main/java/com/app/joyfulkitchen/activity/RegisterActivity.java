package com.app.joyfulkitchen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText register_username;
    private EditText register_passwd;
    private Button register_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_username=(EditText)findViewById(R.id.register_username);
        register_passwd=(EditText)findViewById(R.id.register_passwd);
        register_submit=(Button)findViewById(R.id.register_submit);
        register_username.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(!isEmail(register_username.getText().toString().trim())){
                        Toast.makeText(RegisterActivity.this, "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
       register_passwd.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(register_passwd.getText().toString().trim().length()<8){
                        Toast.makeText(RegisterActivity.this, "密码不能小于8个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(checkEdit()) {

                   final String email = register_username.getText().toString().trim();
                   final String pwd = register_passwd.getText().toString().trim();

                   String path = "http://192.168.191.1:8088/register";

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

                           String str = response.body().string();
                           Log.i("~~~~~~~~~~~~~name:", str);
                           if (str.equals("true")) {
                               Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                               startActivity(intent);
                           }
                       }
                   });

               }
           }
        });




    }
    private boolean checkEdit(){
        if(register_username.getText().toString().trim().equals("")){
            Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }else if(register_passwd.getText().toString().trim().equals("")){
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
       /* }else if(!register_passwd.getText().toString().trim().equals(reregister_passwd.getText().toString().trim())){
            Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();*/
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
