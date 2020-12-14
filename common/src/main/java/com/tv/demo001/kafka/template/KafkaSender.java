package com.tv.demo001.kafka.template;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @Description
 * @Author Allen
 * @Date 2020-09-17 14:40
 **/
@Component
@Slf4j
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    private Gson gson = new GsonBuilder().create();

    public void send(){
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMessage(UUID.randomUUID().toString());
        message.setSendTime(new Date());

        log.info("send message = {}", gson.toJson(message));

        kafkaTemplate.send("com/tv/demo001", "message", gson.toJson(message));
    }

    public void sendWords(){
        String words= "hello,world,hello,spark,hello,hadoop";
        log.info("send words = {}", words);
        kafkaTemplate.send("com/tv/demo001", "word_topic", words);
    }
}
