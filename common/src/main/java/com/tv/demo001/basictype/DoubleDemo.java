package com.tv.demo001.basictype;

public class DoubleDemo {

    public static void main(String[] args) {
        String doubleString = "2.490453262493774E-4";
        DoubleDemo doubleDemo = new DoubleDemo();
        System.out.println(doubleDemo.demo1(doubleString));
    }

    public Double demo1(String doubleString){
        return Double.valueOf(doubleString);
    }
}
