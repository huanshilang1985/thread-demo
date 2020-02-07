package com.zh.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这个类跑出的是错误数据，表示光用volatile和atomicInteger是不能保证线程原子性的，安全的。
 */
public class cas1 {

    private static volatile int m=0;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void increase1(){
        m++;
    }

    public static void increase2(){
        atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) {
        for(int i=0; i<20; i++){
            new Thread(()->{
                cas1.increase1();
            }).start();
        }
        System.out.println("int: " + m);

        for(int i=0; i<20; i++){
            new Thread(()->{
                cas1.increase2();
            }).start();
        }
        System.out.println("atomic: " + atomicInteger.get());
    }
}
