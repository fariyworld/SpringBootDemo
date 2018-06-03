package com.mace.controller.rabbitmq;

import com.mace.common.ResponseMessage;
import com.mace.rabbitmq.fanout.FanoutSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * <br />
 * Created by mace on 18:43 2018/6/3.
 */
@RestController
@RequestMapping("/rabbitmq/fanout")
public class FanoutController {

    @Autowired
    private FanoutSender fanoutSender;

    @RequestMapping(value = "testRabbitMQFanout.do")
    public ResponseMessage testRabbitMQFanout(){

        fanoutSender.send();

        return ResponseMessage.createBySuccess();
    }
}
