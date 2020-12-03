package com.letv.demo001.kafka.origin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OriginSenderTest {

    @Autowired
    private OriginSender sender;

    @Test
    void send() {
        sender.send();
    }
}