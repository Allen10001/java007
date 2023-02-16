package com.tv.demo001.guice.test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

/**
 * @author hubo88
 * @description
 * @date 2022/8/18 5:59 PM
 */
@Singleton
public class GuiceDemo {

//    // 注入
//    @Inject
//    private HelloPrinter helloPrinter;

//    private void demoPrint() {
//        helloPrinter.print(2);
//    }

    public static void main(String[] args) {
        // 1. 获取容器中的对象
        Injector injector = Guice.createInjector();
//        injector.getInstance(HelloPrinter.class);

        // 2.
//        injector.getInstance(GuiceDemo.class).demoPrint();

        // 3. 测试构造器和 @Inject 注释的方法的执行优先级
        HelloPrinter helloPrinter = new HelloPrinter("hello constructor");
        /**
         * Injects dependencies into the fields and methods of instance. Ignores the presence or absence of an injectable constructor.
         * Whenever Guice creates an instance, it performs this injection automatically (after first performing constructor injection),
         * so if you're able to let Guice create all your objects for you, you'll never need to use this method.
         */
        injector.injectMembers(helloPrinter);
//        HelloPrinter helloPrinter1 = injector.getInstance(HelloPrinter.class);
//        helloPrinter1.print(3);

    }

}

class HelloPrinter {

    private String hello = "hello world";
    private String initStr;

    public HelloPrinter(String hello) {
//        System.out.println(hello.isEmpty()); // true
        System.out.println(hello); // true
        this.hello = hello;
    }

    public void print(int i) {
        System.out.println(initStr + i);
    }

    @Inject
    private void init() {
        this.initStr = "initStr";
        System.out.println(initStr);
    }
}
