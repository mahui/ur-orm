package com.memosea.orm.bean;

import java.util.Date;

/**
 * Created by mahui on 2017/3/7.
 */
public class Token {


    private long id;
    private String accessToken;
    private int expiresIn;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", createTime=" + createTime +
                '}';
    }
}
