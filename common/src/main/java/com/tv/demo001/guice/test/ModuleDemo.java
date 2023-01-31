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
        ModuleDemo sample = injector.getInstance(ModuleDemo.class);
        sample.hello();
    }

}


interface IHelloPrinter01 {
    void print();
}

@Singleton
class SimpleHelloPrinter01 implements IHelloPrinter01 {

    @Override
    public void print() {
        System.out.println("Hello, Simple World");
    }
}

@Singleton
class ComplexHelloPrinter01 implements IHelloPrinter01 {
    @Override
    public void print() {
        System.out.println("Hello, Complex World");
    }
}

class SampleModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IHelloPrinter01.class).to(SimpleHelloPrinter01.class);
    }
}