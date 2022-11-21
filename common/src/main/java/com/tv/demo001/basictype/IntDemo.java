package com.tv.demo001.basictype;

/**
 * @author hubo88
 * @description
 * @date 2022/11/21 10:54 AM
 */
public class IntDemo {

    public static void main(String[] args) {
        int min = Integer.MIN_VALUE;
        int  minusMin = -min;
        System.out.println(Integer.toBinaryString(minusMin));
        System.out.println(Integer.toBinaryString(min));
    }
}
