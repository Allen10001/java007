package com.tv.demo001.kafka.origin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description
 * @Author Allen
 * @Date 2020-09-21 10:23
 **/
@Slf4j
public class KafkaManager {

    @Autowired
    private OriginSender sender;

    public static void main(String[] args) {

    }
}
