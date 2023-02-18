package com.tv.demo001.basictype;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.protobuf.ByteString;
import org.springframework.util.Assert;

/**
 * @author hubo88
 * @description
 */
public class LongDemo {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        injector.getInstance(Solution001.class).resolve();
    }
}


@Singleton
class Solution002 {
    public void resolve() {
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

@Singleton
class Solution001 {
    public void resolve() {
        Long value = new Long(1L<<13);
        String binaryStr = Long.toBinaryString(value);
        System.out.println(binaryStr);

        Long newLong = Long.parseLong(binaryStr, 2);
        System.out.println(newLong^value);

        char[] binaryCharArr = binaryStr.toCharArray();
        System.out.println(binaryCharArr.length);
        Assert.isTrue("10000000000000".equals(binaryStr));
    }
}
