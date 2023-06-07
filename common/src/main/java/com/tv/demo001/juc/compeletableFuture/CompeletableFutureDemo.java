package com.tv.demo001.juc.compeletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.checkerframework.checker.units.qual.C;

/**
 * @author hubo88
 * @description
 * @date 2023/5/15 9:01 PM
 */
public class CompeletableFutureDemo {

    public static void main(String[] args) throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompeletableFutureDemo::fetchPrice);
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }
}

/**
  2. CompletionStage 接口，串行执行 异步任务
 */
 class Main001 {
    public static void main(String[] args) throws Exception {
        // 第一个任务: 没参数
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油");
        });  // 异步执行，提交到默认线程池

        // cfQuery成功后继续执行下一个任务: 有参数
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice(code);
        });  // 异步执行，提交到默认线程池
        // cfFetch成功后打印结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        }); // 同步执行
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}

/**
 * 除了串行执行外，多个CompletableFuture还可以并行执行。
 * 只要任意一个返回结果，就继续下一步操作.
 */
class Main002 {

    public static void main(String[] args) throws Exception {
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://finance.sina.com.cn/code/");
        });
        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://money.163.com/code/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://finance.sina.com.cn/price/");
        });
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://money.163.com/price/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);

        // 最终结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code, String url) {
        System.out.println("query price from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}

/**
 * 顺序打印三个字母
 */
class Main003{

    public static void main(String[] args) throws Exception {
        CompletableFuture completableFuture = CompletableFuture
            .runAsync(() -> new T1().start())
            .thenRun(() ->new T2().start())
            .thenRun(() ->new T3().start());
        completableFuture.get();
        CompletableFuture completableFuture2 = CompletableFuture
            .runAsync(() -> new T1().start())
            .thenRun(() ->new T2().start())
            .thenRun(() ->new T3().start());
        completableFuture2.get();
    }
}

class T1 extends Thread{

    @Override
    public void run() {
        System.out.print("A");
    }
}

class T2 extends Thread{

    @Override
    public void run() {
        System.out.print("B");
    }
}

class T3 extends Thread{

    @Override
    public void run() {
        System.out.print("C");
    }
}

class Main004{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.completedFuture(10);
        Integer value = completableFuture.get();
        Integer value2 = completableFuture.get();
        System.out.println(value);
        System.out.println(value2);
    }
}