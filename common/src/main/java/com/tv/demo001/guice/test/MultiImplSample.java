package com.tv.demo001.guice.test;

import com.google.inject.Guice;
import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 一个接口有多个实现
 *
 * @author hubo88
 * @description
 * @date 2022/8/18 6:38 PM
 */
@Singleton
public class MultiImplSample {

    public static final AtomicInteger count = new AtomicInteger(0);

    @Inject
    private IHelloPrinter printer;

    public void hello() {
        printer.print();
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        for (int i=0; i<100; i++) {
            new Thread(() ->{
                // 第一次单例初始化失败后，会不断的走初始化的逻辑, 如果初始化逻辑里面有常驻线程创建逻辑，会导致每次获取单例时都会创建一个线程，最终会导致程序 OOM.
                MultiImplSample sample1 = injector.getInstance(MultiImplSample.class);
            }).start();
        }
//        sample.hello();
    }
}

//@ImplementedBy(ComplexHelloPrinter.class)
@ImplementedBy(SimpleHelloPrinter.class)
interface IHelloPrinter {
    void print();
}

@Singleton
class SimpleHelloPrinter implements IHelloPrinter {

    @Inject
    public void init() throws Exception {
        System.out.println("SimpleHelloPrinter begin.");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName().concat("SimpleHelloPrinter initing."));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setName("Thread:".concat(String.valueOf(MultiImplSample.count.getAndIncrement())));
        thread.start();
        throw new IOException("error");
    }

    @Override
    public void print() {
        System.out.println("Hello, Simple World");
    }

}

@Singleton
class ComplexHelloPrinter implements IHelloPrinter {

    @Inject
    public void init() throws Exception {
        System.out.println("SimpleHelloPrinter begin.");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName().concat("SimpleHelloPrinter initing."));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setName("Thread:".concat(String.valueOf(MultiImplSample.count.getAndIncrement())));
        thread.start();
    }

    @Override
    public void print() {
        System.out.println("Hello, Complex World");
    }

}


