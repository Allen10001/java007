package com.letv.demo001.guava;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuavaDemoTest {

    private GuavaDemo guavaDemo;

    @BeforeEach
    void setUp() {
        guavaDemo = new GuavaDemo();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void main() throws Exception {
        String[] strings = {"addd","bddc"};
        guavaDemo.main(strings);
    }

    @Test
    void testFunction() {
    }
}