package com.letv.demo001;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo001ApplicationTests {

    @Value("${spring.profiles.active}")
    private String profile;

    @Test
    void contextLoads() {
        System.out.println("profile="+profile);
    }

}
