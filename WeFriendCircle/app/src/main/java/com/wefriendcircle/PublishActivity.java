package com.wefriendcircle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.wefriendcircle.utils.HttpUrl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PublishActivity extends AppCompatActivity {
    String publishContent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        ImageButton back_bt = findViewById(R.id.activity_publish_back_imageButton);
        back_bt.setOnClickListener(view->{
            //点击返回朋友圈页面
            Intent intent_back = new Intent(PublishActivity.this,FriendCircleActivity.class);
            startActivity(intent_back);
        });

        Button publish_bt = findViewById(R.id.activity_publish_button);
        publish_bt.setOnClickListener(view->{
            //点击按钮发表朋友圈
            publishContent = ((EditText)findViewById(R.id.activity_publish_content_editText)).getText().toString();//得到要发表的文字数据
            WriteHttpThread writeHttpThread = new WriteHttpThread();
            writeHttpThread.start();
            try {
                writeHttpThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent= new Intent(PublishActivity.this,FriendCircleActivity.class);
            startActivity(intent);
        });
    }

    class WriteHttpThread extends Thread {
        public void run() {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(HttpUrl.URL + "Write");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setConnectTimeout(5 * 1000);
                conn.setReadTimeout(5 * 1000);
                String body = "publishContent=" + URLEncoder.encode(publishContent, "utf-8");
                //设置conn可以写请求内容
                conn.setDoOutput(true);
                conn.getOutputStream().write(body.getBytes());
                int i = conn.getResponseCode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
