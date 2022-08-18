package com.tv.demo001.guice;

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

    // 注入
    @Inject
    private HelloPrinter helloPrinter;

    private void demoPrint() {
        helloPrinter.print(2);
    }

    public static void main(String[] args) {
        // 1. 获取容器中的对象
        Injector injector = Guice.createInjector();
        injector.getInstance(HelloPrinter.class).print(1);

        // 2.
        injector.getInstance(GuiceDemo.class).demoPrint();
    }

}

@Singleton
class HelloPrinter {

    public void print(int i) {
        System.out.println("Hello, World" + i);
    }

}
