package com.tv.demo001.guice;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.javasimon.Sample;

/**
 * 不写 Singleton 时，injector 每次获取实例时会重新构建一个
 * 这个会有反射构造器的性能损耗，在高性能场景下，请谨慎。
 * @author hubo88
 * @description
 * @date 2022/8/18 6:41 PM
 */
public class WithoutSingleton {

    @Inject
    private HelloPrinter01 printer;

    public void hello() {
        printer.print();
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        WithoutSingleton sample = injector.getInstance(WithoutSingleton.class);
        sample.hello();
        sample = injector.getInstance(WithoutSingleton.class);
        sample.hello();
        sample = injector.getInstance(WithoutSingleton.class);
        sample.hello();
        sample = injector.getInstance(WithoutSingleton.class);
        sample.hello();
    }
}

class HelloPrinter01 {

    private static int counter;

    private int myCounter;

    public HelloPrinter01() {
        myCounter = counter++;
    }

    public void print() {
        System.out.printf("Hello, World %d\n", myCounter);
    }

}

