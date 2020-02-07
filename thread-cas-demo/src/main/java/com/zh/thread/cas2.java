package com.zh.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这个类跑出的是错误数据，表示光用volatile和atomicInteger是不能保证线程原子性的，安全的。
 */
public class cas2 {

    private static volatile int m=0;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void increase1(){
        m++;
    }

    public static void increase2(){
        atomicInteger.incrementAndGet();
    }

    /**
     * join方法，使当前线程加入线程组里，表示每个线程都按顺序执行；
     * join线程有了交互性
     */
    public static void main(String[] args) throws Exception{
        Thread[] t = new Thread[20];
        for(int i=0; i<20; i++){
            t[i] = new Thread(()->{
                cas2.increase1();
            });
            t[i].start();
            t[i].join();
        }
        System.out.println("int: " + m);

        Thread[] tf = new Thread[20];
        for(int i=0; i<20; i++){
            tf[i] = new Thread(()->{
                cas2.increase2();
            });
            tf[i].start();
            tf[i].join();
        }
        System.out.println("atomic: " + atomicInteger.get());
    }
}
