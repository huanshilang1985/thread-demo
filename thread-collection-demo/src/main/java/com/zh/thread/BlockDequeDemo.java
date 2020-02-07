package com.zh.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 */
public class BlockDequeDemo {

    public static void main(String[] args) {
        LinkedBlockingDeque<String> list = new LinkedBlockingDeque(3);

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    StringBuffer buffer = new StringBuffer().append(i).append(":").append(j);
                    try {
                        list.put(buffer.toString());
                        System.out.println("client--" + buffer.toString() + "--" + System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    String str = list.take();
                    System.out.println("main---take--" + str + ", size--" + list.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("end");

    }

}
