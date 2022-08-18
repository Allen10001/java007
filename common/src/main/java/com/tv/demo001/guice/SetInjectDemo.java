package com.tv.demo001.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import java.util.Set;

/**
 * @author hubo88
 * @description 可以自动注入 Set，Map 容器，但是得首先加上扩展库
 * @date 2022/8/18 9:17 PM
 */
@Singleton
public class SetInjectDemo {

    @Inject
    private Set<IHelloPrinter004> printers;

    public void hello() {
        for (IHelloPrinter004 printer : printers) {
            printer.print();
        }
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SampleModule004());
        SetInjectDemo sample = injector.getInstance(SetInjectDemo.class);
        sample.hello();
    }
}


interface IHelloPrinter004 {
    void print();
}

@Singleton
class SimpleHelloPrinter004 implements IHelloPrinter004 {

    @Override
    public void print() {
        System.out.println("Hello, Simple World");
    }
}

@Singleton
class ComplexHelloPrinter004 implements IHelloPrinter004 {

    @Override
    public void print() {
        System.out.println("Hello, Complex World");
    }
}

class SampleModule004 extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<IHelloPrinter004> printers01 = Multibinder.newSetBinder(binder(), IHelloPrinter004.class);
        printers01.addBinding().to(SimpleHelloPrinter004.class);
        printers01.addBinding().to(ComplexHelloPrinter004.class);
    }
}