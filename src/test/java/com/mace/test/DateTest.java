package com.mace.test;

import com.mace.kafka.bean.Message;

import java.util.Date;

/**
 * description:
 * <br />
 * Created by mace on 15:02 2018/6/1.
 */
public class DateTest {

    public static void main(String[] args) {

        Message<String> msg = new Message<>();

        msg.setTopic("test");
        msg.setKey("hello");
        msg.setData("世界");
        msg.setSendTime(new Date());

        //Message{topic=test, key=hello, data=世界, sendTime=2018-06-01 15:07:36.319}
        System.out.println(msg);
    }
}
