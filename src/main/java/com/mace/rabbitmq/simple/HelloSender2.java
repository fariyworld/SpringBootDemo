package com.mace.rabbitmq.simple;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description:
 * <br />
 * Created by mace on 21:48 2018/5/19.
 */
@Component
public class HelloSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int count) {
        String context = "hello " + System.currentTimeMillis() + " " + count;
        System.out.println("Sender2 : " + context);
        this.rabbitTemplate.convertAndSend("spring-boot-queue", context);
    }
}
