package com.memosea.orm.bean;

import java.util.Date;

/**
 * Created by mahui on 2017/3/7.
 */
public class Token {


    private Long id;
    private String accessToken;
    private Integer expiresIn;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
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
