package com.tv.demo001.basictype.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hubo88
 * @description
 * @date 2023/11/16 9:49 PM
 */
public class MapDemo001 {

    {
        System.out.println("非匿名内部类实例初始化代码块");
    }


    public static void main(String[] args) {

        // 初始化号码和字母的对应关系
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        Map<Character, String> phoneMap001 = new HashMap<Character, String>() {};

        Thread thread001 = new Thread() {

            {
                System.out.println("匿名内部类实例初始化代码块");
            }
            /**
             * 定义匿名内部类中的方法
             */
            @Override
            public void run() {
                super.run();
            }

        };

        MapDemo001 mapDemo001 = new MapDemo001();
    }
}
