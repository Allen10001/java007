package com.tv.demo001.guice.test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;
import java.util.Map;

/**
 * @author hubo88
 * @description 自动注入 map 容器
 * @date 2022/8/18 9:31 PM
 */
@Singleton
public class MapInjectDemo {

    @Inject
    private Map<String, IHelloPrinter005> printers;

    public void hello() {
        for (String name : printers.keySet()) {
            printers.get(name).print();
        }
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SampleModule005());
        MapInjectDemo sample = injector.getInstance(MapInjectDemo.class);
        sample.hello();
    }

}


interface IHelloPrinter005 {
    void print();
}

@Singleton
class SimpleHelloPrinter005 implements IHelloPrinter005 {

    @Override
    public void print() {
        System.out.println("Hello, Simple World");
    }

}

@Singleton
class ComplexHelloPrinter005 implements IHelloPrinter005 {

    @Override
    public void print() {
        System.out.println("Hello, Complex World");
    }

}

class SampleModule005 extends AbstractModule {

    @Override
    protected void configure() {
        MapBinder<String, IHelloPrinter005> printers = MapBinder.newMapBinder(binder(), String.class, IHelloPrinter005.class);
        printers.addBinding("simple").to(SimpleHelloPrinter005.class);
        printers.addBinding("complex").to(ComplexHelloPrinter005.class);
    }

}