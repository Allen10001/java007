package com.tv.demo001.kafka.origin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OriginSenderTest {

    @Autowired
    private OriginSender sender;

    @Test
    void send() {
        sender.send();
    }
}