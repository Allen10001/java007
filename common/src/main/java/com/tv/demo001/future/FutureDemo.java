package com.tv.demo001.future;

import static co.paralleluniverse.common.util.Debug.exit;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * FutureDemo
 * CompletableFuture 代码执行在哪个线程上 demo 示例.
 * https://tech.meituan.com/2022/05/12/principles-and-practices-of-completablefuture.html
 * @author hubo88
 * @description
 * @date 2025/4/1 下午2:38
 */
public class FutureDemo {

    public static void main(String[] args) {
        FutureDemo futureDemo = new FutureDemo();
        futureDemo.solution();
    }

    public void solution() {
        ExecutorService threadPool1 = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync 执行线程：" + Thread.currentThread().getName());
            //业务操作
            LockSupport.parkNanos(5000000000L);
            return "";
        }, threadPool1);
        //此时，如果future1中的业务操作已经执行完毕并返回，则该thenApply直接由当前main线程执行；否则，将会由执行以上业务操作的threadPool1中的线程执行。
        future1.thenApply(value -> {
            System.out.println("thenApply 执行线程：" + Thread.currentThread().getName());
            return value + "1";
        });

//        //使用ForkJoinPool中的共用线程池CommonPool
//        future1.thenApplyAsync(value -> {
//        //do something
//            System.out.println("thenApply 执行线程：" + Thread.currentThread().getName());
//            return value + "1";
//        });
//        //使用指定线程池
//        future1.thenApplyAsync(value -> {
//            //do something
//            System.out.println("thenApply 执行线程：" + Thread.currentThread().getName());
//            return value + "1";
//        }, threadPool1);
    }


}
