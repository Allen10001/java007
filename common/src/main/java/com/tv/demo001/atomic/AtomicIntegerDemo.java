package com.tv.demo001.atomic;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * map 测试类
 *
 * @author hubo88
 * @description
 * @link
 * @date 2022/3/15 8:52 PM
 */
public class AtomicIntegerDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        new Thread(() -> {
            AtomicIntegerDemo mapDemo = new AtomicIntegerDemo();
            mapDemo.add(atomicInteger);
        }).start();

        Thread.sleep(1000);
        System.out.println(atomicInteger.get());
    }

    public AtomicInteger add(AtomicInteger atomicInteger) {
        atomicInteger.addAndGet(1);
        return atomicInteger;
    }

}
