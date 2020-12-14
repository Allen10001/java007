package com.tv.demo001.kafka.template;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Description
 * @Author Allen
 * @Date 2020-09-17 15:01
 **/
@Component
@Slf4j
public class KafkaReceiver {
    // 如何提交 offset
    @KafkaListener(topics = {"com/tv/demo001"})
    public void listen(ConsumerRecord<?,?> record){
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if(kafkaMessage.isPresent()){
            Object message = kafkaMessage.get();
            log.info("receive message={}", message);
        }
    }

}
