package com.wefriendcircle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wefriendcircle.R;
import com.wefriendcircle.bean.User;
import com.wefriendcircle.utils.HttpUrl;

import java.text.SimpleDateFormat;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<User> mList;
    private Context mContext;
    public ListViewAdapter(Context context, List<User> mList) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);//得到初始化上下文
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        View view;
        if(convertView == null){
            view = mLayoutInflater.from(mContext).inflate(R.layout.layout_publish_to_list,null);
            holder.head_imageView = view.findViewById(R.id.publish_head_imageView);
            holder.publish_imageView = view.findViewById(R.id.publish_image);
            holder.name_textView = view.findViewById(R.id.publish_username_textView);
            holder.date_textView = view.findViewById(R.id.publish_date_textView);
            holder.content_textView = view.findViewById(R.id.publish_content_textView);
            view.setTag(holder);
        }else{
            view = convertView;
            holder=(ViewHolder)convertView.getTag();
        }
        SimpleDateFormat sdf = new SimpleDateFormat();
        holder.name_textView.setText(mList.get(position).getUsername());
        holder.content_textView.setText(mList.get(position).getContent());
        holder.date_textView.setText(sdf.format(mList.get(position).getPublishtime()));
        Glide.with(mContext).load(HttpUrl.URL + mList.get(position).getImageurl()).into(holder.publish_imageView);
        Glide.with(mContext).load(HttpUrl.URL+"image/head.jpg").into(holder.head_imageView);
        return convertView;
    }

    private class ViewHolder {
        private ImageView head_imageView, publish_imageView;
        private TextView name_textView, content_textView, date_textView;
    }
}
