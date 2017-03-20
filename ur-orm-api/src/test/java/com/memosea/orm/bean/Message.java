package com.memosea.orm.bean;

import java.util.Date;

/**
 * Created by mahui on 2017/3/20.
 */
public class Message {
    private Long id;
    private String openId;
    private String msgType;
    private Object msgContent;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Object getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(Object msgContent) {
        this.msgContent = msgContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", msgType='" + msgType + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
