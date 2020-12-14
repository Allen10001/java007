package com.tv.demo001.kafka.origin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class OriginReceiverTest {

    @Autowired
    private OriginReceiver receiver;

    @Test
    void receiveWithAutoCommit() {
        receiver.receiveWithAutoCommit();
    }

    @Test
    void receiveWithManualCommit(){
        receiver.receivedWithManualCommit();
    }

    @Test
    void receivedFixedPartition(){
        receiver.receivedFixedPartition();
    }
}