package com.tv.demo001.theadPool;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * 有返回值的forkjoin任务执行
 *
 * @author hubo88
 * @description
 * @date 2023/4/15 4:22 PM
 */
public class RecursiveTaskDemo extends RecursiveTask<Integer> {

    /**
     *  每个"小任务"最多只打印70个数
     */
    private static final int MAX = 70;
    private int arr[];
    private int start;
    private int end;


    public RecursiveTaskDemo(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 当end-start的值小于MAX时候，开始打印
        if((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        }else {
            System.err.println("=====任务分解======");
            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            RecursiveTaskDemo left = new RecursiveTaskDemo(arr, start, middle);
            RecursiveTaskDemo right = new RecursiveTaskDemo(arr, middle, end);
            // 并行执行两个小任务
            left.fork();   // 主线程提交的会先提交到 common 通用线程队列，通用线程队列里面的任务再去 fork 的时候，会提交到 workQueue，并且计算
            right.fork();
            // 把两个小任务累加的结果合并起来， 执行到这里时 fork 已经有结果了
            return left.join() + right.join();
        }
    }


    public static void main(String[] args) throws Exception {
        int arr[] = new int[1000];
        Random random = new Random();
        int total = 0;
        // 初始化100个数字元素
        for (int i = 0; i < arr.length; i++) {
            int temp = random.nextInt(100);
            // 对数组元素赋值,并将数组元素的值添加到total总和中
            total += (arr[i] = temp);
        }
        System.out.println("初始化时的总和=" + total);

        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 提交可分解的PrintTask任务
    //        Future<Integer> future = forkJoinPool.submit(new RecursiveTaskDemo(arr, 0, arr.length));
    //        System.out.println("计算出来的总和="+future.get());

        Integer integer = forkJoinPool.invoke(new RecursiveTaskDemo(arr, 0, arr.length));
        System.out.println("计算出来的总和=" + integer);

        // 关闭线程池
        forkJoinPool.shutdown();
    }

}
