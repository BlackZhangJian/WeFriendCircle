package com.wefriendcircle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.wefriendcircle.adapter.MyAdapter;
import com.wefriendcircle.bean.User;
import com.wefriendcircle.utils.HttpUrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class FriendCircleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_circle);

        initListView();
        ImageButton back_bt = findViewById(R.id.circle_back_button);
        back_bt.setOnClickListener(view->{
            //点击返回朋友圈页面
            Intent intent_back = new Intent(FriendCircleActivity.this,LoginActivity.class);
            startActivity(intent_back);
        });
        //点击按钮跳转到发朋友圈界面
        ImageButton imageButton = findViewById(R.id.circle_publish_imageButton);
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(FriendCircleActivity.this, PublishActivity.class);
            startActivity(intent);
        });
    }

    private void initListView(){
        //开启Http请求子线程
        PublishHttpThread publishHttpThread = new PublishHttpThread();
        publishHttpThread.start();
        try {
            publishHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //得到服务器发来的数据
        List<User> list = JSON.parseArray(publishHttpThread.getResult(),User.class);
        //设置适配器MyAdapter
        MyAdapter myAdapter = new MyAdapter(this,R.layout.layout_publish_to_list,list);
        ListView mListView = findViewById(R.id.circle_listView);
        mListView.setAdapter(myAdapter);
       /* //设置适配器ListViewAdapter
        ListViewAdapter listViewAdapter = new ListViewAdapter(FriendCircleActivity.this,list);
        ListView mListView = findViewById(R.id.circle_listView);
        mListView.setAdapter(listViewAdapter);*/

    }

    class PublishHttpThread extends Thread{
            private String result;
            public void run() {
                //实现http请求
                try {
                    URL url = new URL(HttpUrl.URL + "Publish");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod(String.valueOf("POST"));
                    httpURLConnection.setConnectTimeout(5 * 1000);
                    InputStream is = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(is, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuffer stringBuffer = new StringBuffer();
                    String temp;
                    while ((temp = bufferedReader.readLine()) != null) {
                        stringBuffer.append(temp);
                    }
                    setResult(stringBuffer.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            private String getResult() {
                return result;
            }

            private void setResult(String result) {
                this.result = result;
            }
    }
}