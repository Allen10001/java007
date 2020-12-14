package com.tv.demo001;

/**
 * @Description
 * @Author Allen
 * @Date 2020-09-14 17:13
 **/
public class ShutdownHookTest {
    public static void main(String[] args) throws Exception{

        System.out.println("++++++");
        Thread.sleep(20000);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("---------");
            }
        }));

    }
}
