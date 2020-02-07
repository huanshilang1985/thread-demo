package com.zh.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 这个例子演示的是ABA原理，原来的的A值100，改成B值110后，之后又被再次改成了A值100
 */
public class cas3 {

    private static volatile int m = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    // 初始值，版本号
    private static AtomicStampedReference asr = new AtomicStampedReference(100, 1);


    public static void main(String[] args) throws InterruptedException {
        //线程1，原A值100，改成了B值110
        Thread t1 = new Thread(() -> {
            System.out.println(atomicInteger.compareAndSet(100, 110));
        });
        t1.start();

        //线程2，原B值110，改成了A值100
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicInteger.compareAndSet(110, 100));
        });
        t2.start();

        // 线程3，把值100改成了120，并不关心之前是否发生了变化
        Thread t3 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicInteger.compareAndSet(100, 110));
        });
        t3.start();

        System.out.println("=================");

        Thread tf1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("AtomicStampedReference 1==" + asr.compareAndSet(100, 110, asr.getStamp(), asr.getStamp() + 1));
            System.out.println("AtomicStampedReference 2==" + asr.compareAndSet(110, 100, asr.getStamp(), asr.getStamp() + 1));
        });

        Thread tf2 = new Thread(() -> {
            // 提前拿到版本号
            int stamp = asr.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 暂停2秒之后，版本号会因为tf1线程发生变化，所以为false
            System.out.println("AtomicStampedReference 3==" + asr.compareAndSet(100, 120, stamp, asr.getStamp() + 1));
        });
        tf1.start();
        tf2.start();
    }

}
