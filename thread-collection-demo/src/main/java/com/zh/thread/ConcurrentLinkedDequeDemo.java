package com.zh.thread;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 非阻塞的并发集合ConcurrentLinkedDeque
 * 一边添加数据，一边移除数据
 * 在并发环境下，还要保证集合的一致性，就可以用并发集合类
 */
public class ConcurrentLinkedDequeDemo {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        // 添加数据
        Thread[] add = new Thread[100];
        for (int i = 0; i < 100; i++) {
            add[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    // 添加数据
                    list.add(Thread.currentThread().getName() + ":Element" + j);
                }
            });
            add[i].start();
            //使用join保证有序执行
            add[i].join();
        }
        System.out.println("after add size : " + list.size());

        // 移除数据，消费数据
        Thread[] poll = new Thread[100];
        for (int i = 0; i < 100; i++) {
            poll[i] = new Thread(() -> {
                for (int j = 0; j < 5000; j++) {
                    // 移除最后一条数据
                    list.pollLast();
                    // 移除第一条数据
                    list.pollFirst();
                }
            });
            poll[i].start();
            poll[i].join();
        }
        System.out.println("after poll size : " + list.size());


    }


}
