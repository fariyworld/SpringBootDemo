package com.mace.rabbitmq.simple;

import com.mace.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description:
 * <br />
 * Created by mace on 21:48 2018/5/19.
 */
@Component
public class HelloSender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int count) {
        String context = "hello " + System.currentTimeMillis() + " " + count;
        System.out.println("Sender1 : " + context);
        this.rabbitTemplate.convertAndSend("spring-boot-queue", context);
    }

    public void send(User user) {

        System.out.println("Sender1 object: " + user.toString());
        this.rabbitTemplate.convertAndSend("spring-boot-queue", user);
    }
    //发送send1会匹配到topic.#和topic.message 两个Receiver都可以收到消息，
    //发送send2只有topic.#可以匹配所有只有Receiver2监听到消息
    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender1 : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", context);
    }

    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender1 : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", context);
    }
}
