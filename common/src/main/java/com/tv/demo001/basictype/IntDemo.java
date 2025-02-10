package com.tv.demo001.basictype;

import com.google.common.collect.Lists;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
import org.openjdk.jol.info.GraphLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * int 测试
 *
 * @author hubo88
 * @description
 * @date 2023/11/13 7:41 PM
 */
public class IntDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntDemo.class);

    public static void main(String[] args) throws InterruptedException {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();

        Thread.sleep(10000);
        LOGGER.error("===heap_used_size1: {}" , Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        LOGGER.error("===heap_used_size2: {}" , heapMemoryUsage.getUsed());
        Integer a = new Integer(10000);
        int b = 20000;
        System.out.println(a);
        System.out.println(GraphLayout.parseInstance(a).totalSize());
        System.out.println(GraphLayout.parseInstance(b).totalSize());

        Thread.sleep(10000);
        LOGGER.error("===heap_used_size1: {}" , Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        LOGGER.error("===heap_used_size2: {}" , heapMemoryUsage.getUsed());

        Thread.sleep(10000);
        LOGGER.error("===heap_used_size1: {}" , Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        LOGGER.error("===heap_used_size2: {}" , heapMemoryUsage.getUsed());
        for (int i=0; i<1000000; i++) {
            int a3 = new Integer(10001);
//            System.out.println(a3);
        }
        Thread.sleep(60000);
        LOGGER.error("===heap_used_size1: {}" , Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        LOGGER.error("===heap_used_size2: {}" , heapMemoryUsage.getUsed());
        Thread.sleep(10000);
    }
}
