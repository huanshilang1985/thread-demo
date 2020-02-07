package com.zh.thread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 使用CyclicBarrier模式跑步比赛，让所有人都准备好之后，所有线程一起执行
 */
public class RaceDemo {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(8);
        Thread[] play = new Thread[8];
        for(int i = 0; i < 8 ; i++){
            play[i] = new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + "准备好了");
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("选手" +Thread.currentThread().getName()+"起跑");
            }, "play["+i+"]");
            play[i].start();
        }
    }
}
