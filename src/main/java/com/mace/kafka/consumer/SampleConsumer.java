package com.mace.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * description:
 * <br />
 * Created by mace on 14:27 2018/6/1.
 */
@Component
@Slf4j
public class SampleConsumer {

    @KafkaListener(topics = {"test"})
//    @KafkaListener(topics = {"${spring.kafka.topics}"})
    public void listen(ConsumerRecord<?, ?> record) {

        log.info("key: " + record.key());
        log.info("value: " + record.value().toString());
    }
}
