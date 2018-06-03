package com.mace.controller.rabbitmq;

import com.mace.common.ResponseMessage;
import com.mace.domain.User;
import com.mace.rabbitmq.simple.HelloSender1;
import com.mace.rabbitmq.simple.HelloSender2;
import com.mace.service.mysql.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * <br />
 * Created by mace on 18:38 2018/6/3.
 */
@RestController
@RequestMapping("/rabbitmq/simple")
public class SimpleController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender2 helloSender2;


    @RequestMapping(value = "testRabbitMQ.do")
    public ResponseMessage<String> testRabbitMQ(){

        helloSender1.send(0);

        return ResponseMessage.createBySuccess();
    }

    @RequestMapping(value = "testRabbitMQOneToMany.do")
    public ResponseMessage<String> testRabbitMQOneToMany(){

        for (int i=0;i<10;i++){
            helloSender1.send(i);
        }

        return ResponseMessage.createBySuccess();
    }


    @RequestMapping(value = "testRabbitMQManyToMany.do")
    public ResponseMessage<String> testRabbitMQManyToMany(){

        for (int i=0;i<10;i++){
            helloSender1.send(i);
            helloSender2.send(i);
        }

        return ResponseMessage.createBySuccess();
    }

    @RequestMapping(value = "testRabbitMQObject.do")
    public ResponseMessage<User> testRabbitMQObject(){

        User user = iUserService.selectByPrimaryKey(1);

        helloSender1.send(user);

        return ResponseMessage.createBySuccess(user);
    }
}
