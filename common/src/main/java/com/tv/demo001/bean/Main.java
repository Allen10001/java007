package com.tv.demo001.bean;


/**
 * 入口
 *
 * @author hubo88
 * @description
 * @date 2024/5/29 11:03 AM
 */
public class Main {

    public static void main(String[] args) {
        ChildVertex childVertex = new ChildVertex(1, "billy");
        System.out.println(childVertex.getName());
        System.out.println(childVertex.alias);
//        System.out.println(childVertex.alias001);
    }
}
