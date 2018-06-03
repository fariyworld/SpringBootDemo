package com.mace.rabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * <br />
 * Created by mace on 19:46 2018/5/20.
 */
@Configuration
public class TopicRabbitConfig {


    @Bean
    public Queue queueMessage() {
        return new Queue("topic.message",false);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue("topic.messages",false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    @Bean
    public Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }
    //使用queueMessages同时匹配两个队列，queueMessage只匹配"topic.message"队列
    @Bean
    public Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
