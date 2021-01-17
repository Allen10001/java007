package com.tv.demo001.simon;

import org.javasimon.SimonManager;
import org.javasimon.Split;
import org.javasimon.Stopwatch;

public class Simon007 {
    public static void main(String[] args) {
        Stopwatch stopwatch = SimonManager.getStopwatch("use.basic");
        Split split = stopwatch.start();
        doVitalTask();
        split.stop();

        System.out.println(stopwatch.getCounter());
        System.out.println(stopwatch.getMax()/1000/1000);       //Convert nano-secs to milli-secs
        System.out.println(stopwatch.getMin()/1000/1000);
        System.out.printf("%.2f%n", stopwatch.getMean()/1024/1024);
        System.out.println(stopwatch.getTotal()/1000/1000);
    }

    private static void doVitalTask()
    {
        try
        {
            Thread.sleep(100);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
