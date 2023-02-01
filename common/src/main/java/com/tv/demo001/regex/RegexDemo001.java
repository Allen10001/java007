package com.tv.demo001.regex;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则方法测试
 *
 * @author hubo88
 * @description
 * @date 2023/1/31 10:51 AM
 */
public class RegexDemo001 {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        injector.getInstance(Application.class).run();
    }
}

@Singleton
class Application {

    @Inject
    private Solution1 solution1;

    void run() {
        solution1.resolve();
        System.out.println(solution1);
    }
}

//@Singleton
class Solution1 {

    static final Pattern pattern001 = Pattern.compile("\\$\\w+");
    static final Pattern pattern002 = Pattern.compile("#\\w+");

    private String exp;

    // 相比下边的构造器方法，@Inject 修饰的普通方法优先级更高
    @Inject
    public void initM() {
        this.exp = "$aaa, bbb, $ccc, ddd, fff, #ddd";
//        System.out.println("$aaa, bbb, $ccc, ddd, fff, #ddd");
    }

    /**
     * Classes must have either one (and only one) constructor annotated with @Inject or a zero-argument constructor that is not private.
     */
    @Inject
    public Solution1(String a) {
        this.exp = "$aaa, bbb, $ccc, ddd, $eee, fff, #ddd";;
    }



    void resolve() {

        String exp001 = "$aaa";
        Matcher m = pattern001.matcher(exp);
        Matcher m2 = pattern002.matcher(exp);
        Matcher m1 = pattern001.matcher(exp001);
        boolean a=m.find(), b = m2.find();

        while (a || b) {

            if (a) {
                System.out.println(exp.substring(m.start(), m.end()));
            }

            if (b) {
                System.out.println(exp.substring(m2.start(), m2.end()));
            }
            a = m.find();
            b = m2.find();
        }

    }
}
