package com.wefriendcircle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wefriendcircle.utils.HttpUrl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    String username;
    String password;
    String confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    private Handler mhandler = new Handler(msg -> {
        if(msg.what==2){
            Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_LONG).show();
        }
        return false;
    });
    public void registerClick(View v){
        username = ((EditText)findViewById(R.id.register_user_editText)).getText().toString();
        password = ((EditText)findViewById(R.id.register_password_editText)).getText().toString();
        confirmPassword = ((EditText)findViewById(R.id.register_confirm_password_editText)).getText().toString();
        if(username.isEmpty()){
            Toast.makeText(RegisterActivity.this,"请输入用户名",Toast.LENGTH_LONG).show();
        }else if(password.isEmpty()){
            Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_LONG).show();
        }else if(confirmPassword.isEmpty()){
            Toast.makeText(RegisterActivity.this,"请确认密码",Toast.LENGTH_LONG).show();
        }else if(password.equals(confirmPassword)){
            registerHttpRequest();
        }else{
            Toast.makeText(RegisterActivity.this,"密码不一致",Toast.LENGTH_LONG).show();
        }
    }

    private void registerHttpRequest(){
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                try {
                    URL url = new URL(HttpUrl.URL + "Register");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(5 * 1000);
                    conn.setReadTimeout(5 * 1000);
                    String body = "username=" + URLEncoder.encode(username, "utf-8") + "&password=" + URLEncoder.encode(password, "utf-8");
                    //设置conn可以写请求内容
                    conn.setDoOutput(true);
                    conn.getOutputStream().write(body.getBytes());
                    if ((conn.getResponseCode() == 200)) {
                        InputStream inputStream = conn.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        //StringBuilder response = new StringBuilder();
                        String temp;
                        temp = bufferedReader.readLine();
                        Message msg = new Message();
                        if(temp.equals("REGISTERSUCCESS")){
                            Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            msg.what = 2;
                        }
                        mhandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}