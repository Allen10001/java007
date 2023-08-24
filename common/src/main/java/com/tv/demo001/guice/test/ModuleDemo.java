package com.tv.demo001.guice.test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

/**
 * @author hubo88
 * @description 使用Guice Module定义装配规则定义接口的实现类
 * @date 2022/8/18 8:48 PM
 */
@Singleton
public class ModuleDemo {

    @Inject
    private IHelloPrinter printer;

    public void hello() {
        printer.print();
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SampleModule());
        SimpleHelloPrinter01 sample1 = injector.getInstance(SimpleHelloPrinter01.class);
        SimpleHelloPrinter01 sample2 = injector.getInstance(SimpleHelloPrinter01.class);
        SimpleHelloPrinter01 sample3 = injector.getInstance(SimpleHelloPrinter01.class);
        SimpleHelloPrinter01 sample4 = injector.getInstance(SimpleHelloPrinter01.class);
        sample1.print();
    }

}


interface IHelloPrinter01 {
    void print();
}

@Singleton
class SimpleHelloPrinter01 implements IHelloPrinter01 {

    @Inject
    private void init() {
        System.out.println("simple");
    }

    @Override
    public void print() {
        System.out.println("Hello, Simple World");
    }
}

//@Singleton
class ComplexHelloPrinter01 implements IHelloPrinter01 {

    @Inject
    private void init() {
        System.out.println("complex");
    }

    @Override
    public void print() {
        System.out.println("Hello, Complex World");
    }
}

class SampleModule extends AbstractModule {
    @Override
    protected void configure() {
//        bind(IHelloPrinter01.class).to(SimpleHelloPrinter01.class);
        bind(SimpleHelloPrinter01.class);
    }
}