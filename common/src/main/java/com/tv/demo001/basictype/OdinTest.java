package com.tv.demo001.basictype;

/**
 * @author hubo88
 * @description
 * @date 2023/6/7 6:43 PM
 */
public class OdinTest {

    public static void main(String[] args) {
        if ("itaas".equals(System.getenv("env")) || "test".equals(System.getenv("platform"))) {
            System.out.println(System.getenv("env").concat("   ").concat(System.getenv("platform")));
        } else {
            System.out.println(System.getenv("env").concat("   ").concat(System.getenv("platform")));
        }
    }
}
