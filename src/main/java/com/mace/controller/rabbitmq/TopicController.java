package com.mace.controller.rabbitmq;

import com.mace.common.ResponseMessage;
import com.mace.rabbitmq.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * <br />
 * Created by mace on 18:42 2018/6/3.
 */
@RestController
@RequestMapping("/rabbitmq/topic")
public class TopicController {

    @Autowired
    private TopicSender topicSender;


    @RequestMapping(value = "testRabbitMQTopic.do")
    public ResponseMessage testRabbitMQTopic(){

        topicSender.send();
        topicSender.send1();
        topicSender.send2();

        return ResponseMessage.createBySuccess();
    }
}
