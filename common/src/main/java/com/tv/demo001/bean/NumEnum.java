package com.tv.demo001.bean;

/**
 * bad case, 枚举类的成员不要定义成中文的
 */
@Deprecated
public enum NumEnum {

    一(1),二(2);

    private int num;

    NumEnum(int num) {
        this.num = num;
    }

    public static void main(String[] args) {
        System.out.println(NumEnum.一.name());
    }

}
