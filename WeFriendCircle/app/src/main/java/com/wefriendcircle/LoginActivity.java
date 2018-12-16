package com.wefriendcircle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wefriendcircle.utils.HttpUrl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    String username;
    String password;
    TextView linkRegister;
    TextView forgetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //点击注册进入注册页面
        linkRegister=findViewById((R.id.register_textView));
        linkRegister.setOnClickListener(view -> {
            Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        //点击忘记密码提示
        forgetPassword=findViewById(R.id.forget_password_textView);
        forgetPassword.setOnClickListener(view -> {
            Toast.makeText(LoginActivity.this,"此功能维护中",Toast.LENGTH_LONG).show();
        });

    }
    private Handler mhandler = new Handler(msg -> {
        if(msg.what==1){
            Toast.makeText(LoginActivity.this,"登录失败，用户名或密码错误",Toast.LENGTH_LONG).show();
        }else if(msg.what==2){
            Toast.makeText(LoginActivity.this,"登录失败，无法连接服务器",Toast.LENGTH_LONG).show();
        }
        return false;
    });
    //监听实现登录
    public void loginClick(View v) {
        username = ((EditText)findViewById(R.id.login_user_editText)).getText().toString();
        password = ((EditText)findViewById(R.id.login_password_editText)).getText().toString();

        if(username.isEmpty()){
            Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_LONG).show();
        }else if(password.isEmpty()){
            Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_LONG).show();
        }else{
            //用户名和密码传到服务器进行验证登录，验证成功后跳转进入朋友圈界面
            loginHttpRequest();
        }
    }
    private void loginHttpRequest(){
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                try {
                    URL url = new URL(HttpUrl.URL + "Login");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(5 * 1000);
                    conn.setReadTimeout(5 * 1000);
                    String body = "username=" + URLEncoder.encode(username, "utf-8") + "&password=" + URLEncoder.encode(password, "utf-8");
                    //设置conn可以写请求内容
                    conn.setDoOutput(true);
                    conn.getOutputStream().write(body.getBytes());
                    int i = conn.getResponseCode();
                    if ((conn.getResponseCode() == 200)) {
                        InputStream inputStream = conn.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        //StringBuilder response = new StringBuilder();
                        String temp;
                        temp = bufferedReader.readLine();
                        Message msg = new Message();
                        if(temp.equals("LOGINSUCCESS")){
                            //进入朋友圈界面
                            Intent intent=new Intent(LoginActivity.this, FriendCircleActivity.class);
                            startActivity(intent);
                        }else{
                            //登录失败
                            msg.what = 1;
                        }
                        mhandler.sendMessage(msg);
                    }else{
                        //无法连接服务器
                        Message msg = new Message();
                        msg.what = 2;
                        mhandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    //无法连接服务器
                    Message msg = new Message();
                    msg.what = 2;
                    mhandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
