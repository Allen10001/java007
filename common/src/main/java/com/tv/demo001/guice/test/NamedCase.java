package com.tv.demo001.guice.test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * @author hubo88
 * @description 使用@Named名称指令来指定依赖注入实现
 * 注入：字段注入
 *      构造器注入
 * @date 2022/8/18 8:57 PM
 */
@Singleton
public class NamedCase {

    // 字段注入
//    @Inject
    @Named("simple")
    private IHelloPrinter003 simplePrinter;

    // 字段注入
//    @Inject
    @Named("complex")
    private IHelloPrinter003 complexPrinter;

    public void hello() {
        simplePrinter.print();
        complexPrinter.print();
    }

    // 构造器注入
    @Inject
    public NamedCase(@Named("simple") IHelloPrinter003 simplePrinter, @Named("complex") IHelloPrinter003 complexPrinter) {
        this.simplePrinter = simplePrinter;
        this.complexPrinter = complexPrinter;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SampleModule003());
        NamedCase sample = injector.getInstance(NamedCase.class);
        sample.hello();

        IHelloPrinter003 iHelloPrinterComplex = injector.getInstance(Key.get(IHelloPrinter003.class, Names.named("complex")));
        iHelloPrinterComplex.print();

        IHelloPrinter003 iHelloPrinterSimple = injector.getInstance(Key.get(IHelloPrinter003.class, Names.named("simple")));
        iHelloPrinterSimple.print();
    }

}

interface IHelloPrinter003 {
    void print();
}

@Singleton
class SimpleHelloPrinter003 implements IHelloPrinter003 {

    @Override
    public void print() {
        System.out.println("Hello, Simple World");
    }
}

@Singleton
class ComplexHelloPrinter003 implements IHelloPrinter003 {

    @Override
    public void print() {
        System.out.println("Hello, Complex World");
    }
}

class SampleModule003 extends AbstractModule {

    @Override
    protected void configure() {
        bind(IHelloPrinter003.class).annotatedWith(Names.named("simple")).to(SimpleHelloPrinter003.class);
        bind(IHelloPrinter003.class).annotatedWith(Names.named("complex")).to(ComplexHelloPrinter003.class);
    }

}