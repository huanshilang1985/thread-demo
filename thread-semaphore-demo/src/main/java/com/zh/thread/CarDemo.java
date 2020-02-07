package com.zh.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 使用Semaphore模拟停车场场景
 */
public class CarDemo {

    public static void main(String[] args) {
        //创建Semaphore，模拟停车场
        Semaphore semaphore = new Semaphore(5);

        // 请求许可，模拟汽车要使用停车场
        Thread[] car = new Thread[10];
        for (int i= 0 ; i <10;i++) {
            car[i] = new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"可以进停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 使用资源，在停车场里停留一段时间
                try {
                    int stopTime = new Random().nextInt(10);
                    TimeUnit.SECONDS.sleep(stopTime);
                    System.out.println(Thread.currentThread().getName()+"使用了停车场"+stopTime+"秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 离开（释放资源）
                try {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"离开停车场");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "car["+i+"]");
            car[i].start();
        }
    }

}
