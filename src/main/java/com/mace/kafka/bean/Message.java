package com.mace.kafka.bean;


import com.mace.util.DateUtil;

import java.util.Date;

/**
 * description:
 * <br />
 * Created by mace on 11:42 2018/6/1.
 */
public class Message<T> {

    private String topic;
    private T key;
    private T data;
    private Date sendTime;

    public Message(String topic, T data, Date sendTime) {
        this.topic = topic;
        this.data = data;
        this.sendTime = sendTime;
    }

    public Message(String topic, T key, T data, Date sendTime) {
        this.topic = topic;
        this.key = key;
        this.data = data;
        this.sendTime = sendTime;
    }

    public Message() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("topic=").append(topic);
        sb.append(", key=").append(key);
        sb.append(", data=").append(data);
        sb.append(", sendTime=").append(DateUtil.formatDate(sendTime));
        sb.append('}');
        return sb.toString();
    }
}
