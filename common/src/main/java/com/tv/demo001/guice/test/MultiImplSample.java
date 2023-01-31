package com.tv.demo001.guice.test;

import com.google.inject.Guice;
import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

/**
 * 一个接口有多个实现
 *
 * @author hubo88
 * @description
 * @date 2022/8/18 6:38 PM
 */
@Singleton
public class MultiImplSample {

    @Inject
    private IHelloPrinter printer;

    public void hello() {
        printer.print();
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        MultiImplSample sample = injector.getInstance(MultiImplSample.class);
        sample.hello();
    }
}

@ImplementedBy(SimpleHelloPrinter.class)
interface IHelloPrinter {
    void print();
}

@Singleton
class SimpleHelloPrinter implements IHelloPrinter {

    @Override
    public void print() {
        System.out.println("Hello, Simple World");
    }

}

@Singleton
class ComplexHelloPrinter implements IHelloPrinter {

    @Override
    public void print() {
        System.out.println("Hello, Complex World");
    }

}


