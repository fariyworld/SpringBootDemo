package com.mace.fastjson.test;

import com.mace.entity.Dept;
import com.mace.fastjson.util.FastJsonUtil;
import com.mace.kafka.bean.Message;
import com.mace.util.DateUtil;

import java.util.Date;

/**
 * description: alibaba.fastjson工具类
 * <br />
 * Created by mace on 9:24 2018/5/29.
 */
public class TestFastJson {

    public static void main(String[] args) {

        Message<Dept> deptMessage = new Message<>();

        Dept dept = new Dept();
        dept.setId(1);
        dept.setDname("研发中心");
        dept.setEptno(101);
        dept.setLoc("西安");

        deptMessage.setTopic("test");
        deptMessage.setSendTime(DateUtil.formatDate(new Date()));
        deptMessage.setData(dept);
        deptMessage.setKey("test-dept");

        String jsonString = FastJsonUtil.toJSONString(deptMessage);

        System.out.println(jsonString);

        Message message = FastJsonUtil.toBean(jsonString, Message.class);

        System.out.println(message);
    }
}
