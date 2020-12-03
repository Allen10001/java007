package com.letv.demo001.kafka.origin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


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