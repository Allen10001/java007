package com.tv.demo001.atomic;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import org.apache.commons.collections4.ListUtils;

/**
 * map 测试类
 *
 * @author hubo88
 * @description
 * @link
 * @date 2022/3/15 8:52 PM
 */
public class AtomicIntegerDemo {

    public static void main(String[] args) {
        try {
            CompletableFuture completedFuture = CompletableFuture.supplyAsync(() -> {
//                LockSupport.parkNanos(1000000000L);
                return 2;
            });

            completedFuture.thenAccept(result -> {
                    System.out.println("bbb"+System.currentTimeMillis());
                    LockSupport.parkNanos(3000000000L);
                    System.out.println("bbb"+System.currentTimeMillis());
                }).orTimeout(2000, TimeUnit.MILLISECONDS)
                .exceptionally(e -> {
                    System.out.println("ccc"+System.currentTimeMillis());
                    ((Throwable)e).printStackTrace();
                    return null;
                }).get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }
}
