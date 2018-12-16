package com.wefriendcircle.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wefriendcircle.R;
import com.wefriendcircle.bean.User;
import com.wefriendcircle.utils.HttpUrl;

import java.text.SimpleDateFormat;
import java.util.List;


public class MyAdapter extends ArrayAdapter {
    private int resourceId;
    public MyAdapter(Context context,
                     int resource,
                     List objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    static class ViewHolder {
        private ImageView head_imageView, publish_imageView;
        private TextView name_textView, content_textView, date_textView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = (User) getItem(position);
        ViewHolder holder = new ViewHolder();
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            holder.head_imageView = view.findViewById(R.id.publish_head_imageView);
            holder.publish_imageView = view.findViewById(R.id.publish_image);
            holder.name_textView = view.findViewById(R.id.publish_username_textView);
            holder.date_textView = view.findViewById(R.id.publish_date_textView);
            holder.content_textView = view.findViewById(R.id.publish_content_textView);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.name_textView.setText(user.getUsername());
        holder.content_textView.setText(user.getContent());
        holder.date_textView.setText(sdf.format(user.getPublishtime()));
        Glide.with(getContext()).load(HttpUrl.URL + user.getImageurl()).into(holder.publish_imageView);
        Glide.with(getContext()).load(HttpUrl.URL+"images/head.jpg").into(holder.head_imageView);
        return view;
    }
}