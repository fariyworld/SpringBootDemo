package com.mace.controller.kafka;

import com.mace.common.ResponseMessage;
import com.mace.kafka.bean.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: kafka 入门 controller
 * <br />
 * Created by mace on 11:33 2018/6/1.
 */
@RestController
@RequestMapping("/kafka")
@Slf4j
public class SampleController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @RequestMapping(value = "send.do")
    public ResponseMessage<String> send(Message<String> message){

        log.info("将要发送的消息：{}",message);
        log.info("kafka default topic: {}",kafkaTemplate.getDefaultTopic());

        kafkaTemplate.send(message.getTopic(), message.getKey(), message.getData());

        return ResponseMessage.createBySuccessMessage("发送成功");
    }


}
