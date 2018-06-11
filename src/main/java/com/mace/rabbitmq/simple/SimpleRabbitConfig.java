package com.mace.rabbitmq.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * <br />
 * Created by mace on 19:03 2018/6/3.
 */
@Configuration
public class SimpleRabbitConfig {

    @Bean
    public Queue queue(){
        //true 队列持久
        return new Queue("spring-boot-queue", false);
    }


    /**
     * description:  redis 离线购物车消息队列
     * <br /><br />
     * create by mace on 2018/6/11 11:50.
     * @param
     * @return: org.springframework.amqp.core.Queue
     */
    @Bean
    public Queue RedisOffLineCartMessage() {
        return new Queue("redis_off_line_cart", true);
    }

}
