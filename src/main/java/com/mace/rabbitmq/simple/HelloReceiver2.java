package com.mace.rabbitmq.simple;

import com.mace.domain.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * description:
 * <br />
 * Created by mace on 15:39 2018/5/20.
 */
@Component
@RabbitListener(queues = "spring-boot-queue")
public class HelloReceiver2 {


    @RabbitHandler
    public void process(String hello) {

        System.out.println("Receiver2  : " + hello);
    }

    @RabbitHandler
    public void process(User user) {

        System.out.println("Receiver2 object : " + user);
    }
}
