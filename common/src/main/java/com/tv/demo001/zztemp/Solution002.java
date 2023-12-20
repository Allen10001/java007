package com.tv.demo001.zztemp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class Solution003{

    public static void main(String[] args) throws InterruptedException {
        Solution003 solution003 = new Solution003();
        solution003.solution();
    }

    private AtomicInteger count = new AtomicInteger(0);

    public void solution() throws InterruptedException {
        CountDownLatch countDown = new CountDownLatch(100);
        for (int i=0; i< 100; i++) {
            new Thread() {
                @Override
                public void run() {
                    count.addAndGet(1);
                    countDown.countDown();
                }
            }.start();
        }
        countDown.await();

        System.out.println(count.get());
    }
}
