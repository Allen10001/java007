package com.tv.demo001.field;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 字段隐藏demo示例
 *
 * @author hubo88
 * @description
 * @date 2025/4/7 上午11:08
 */
public class ParentAndChildDemo {

    static class Parent {
        protected int x = 10;  // 父类 protected 字段
        public void printFieldsNoChild() {
            System.out.println("父类 x = " + x);    // 显式访问父类字段（10）
        }
    }

    static class Child extends Parent {
        protected int x = 20;  // 子类同名 protected 字段

        public void printFields() {
            System.out.println("子类 x = " + x);          // 输出子类字段（20）
            System.out.println("父类 x = " + super.x);    // 显式访问父类字段（10）
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        Child child = new Child();
        child.printFields();

        // 通过父类引用访问字段（编译时类型决定）
        Parent parent = child;
        System.out.println("通过父类引用访问 x = " + parent.x);  // 输出 10

        parent.printFieldsNoChild();  // 输出 10, 字段没有多态

        // 反射方式解决字段没有多态问题, 统一父类子类同字段的值
        System.out.println("===反射方式解决字段没有多态问题===");
        List<Field> fields = getAllFields(new ArrayList<>(), Child.class, null);
        for (Field field : fields) {
            System.out.println(field.getDeclaringClass());
            field.setAccessible(true);
            field.set(child, 30);
        }
        child.printFields();
        // 通过父类引用访问字段（编译时类型决定）
        Parent parent2 = child;
        System.out.println("通过父类引用访问 x = " + parent2.x);  // 输出 30
        parent.printFieldsNoChild();  // 输出 30
    }

    public static List<Field> getAllFields(
        List<Field> fields, Class<?> clazz, Predicate<Field> predicate) {
        List<Field> declaredFields = Arrays.stream(clazz.getDeclaredFields())
            .filter(field -> predicate == null || predicate.test(field))
            .collect(Collectors.toList());
        fields.addAll(declaredFields);

        if (clazz.getSuperclass() != null) {
            getAllFields(fields, clazz.getSuperclass(), predicate);
        }

        return fields;
    }
}
