package com.mace.kafka.bean;

/**
 * description:
 * <br />
 * Created by mace on 11:42 2018/6/1.
 */
public class Message<T> {

    private String topic;
    private T key;
    private T data;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("topic='").append(topic).append('\'');
        sb.append(", key=").append(key);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
