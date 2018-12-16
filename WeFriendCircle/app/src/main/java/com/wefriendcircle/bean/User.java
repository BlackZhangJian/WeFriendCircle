package com.wefriendcircle.bean;

import java.sql.Timestamp;

public class User {
    private Timestamp publishtime;
    private String username;
    private String password;
    private String content;
    private String imageurl;


    public User() {
    }

    public User(Timestamp publishtime, String username, String password, String content, String imageurl) {
        super();
        this.publishtime = publishtime;
        this.username = username;
        this.password = password;
        this.content = content;
        this.imageurl = imageurl;
    }

    public Timestamp getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Timestamp publishtime) {
        this.publishtime = publishtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [publishtime=" + publishtime + ", username=" + username + ", password=" + password + ", content="
                + content + ", imageurl=" + imageurl + "]";
    }
}