package com.tv.demo001.basictype;

import com.google.protobuf.ByteString;

/**
 * @author hubo88
 * @description
 */
public class LongDemo {

    public static void main(String[] args) {
        Long v1 = 2L;
        Long v2 = 45L;
        Float res1 = (float)v1/v2;
        Float res2 = (float)(v1/v2);
        System.out.println(res1);
        System.out.println(res2);

        Long v3 = 5000L;
        Double themeScore = (double)v1/v3 + Math.sqrt(Math.log(v3)/v2);
        System.out.println(themeScore);

        System.out.println(ByteString.copyFromUtf8("1500002114"));
    }
}
