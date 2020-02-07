package com.zh.thread;

import java.util.concurrent.TimeUnit;

/**
 * 异常测试，测试结果会出现重复值
 */
public class Demo {

    private int m = 0;

    public int next() {
        try {
            TimeUnit.SECONDS.sleep(2);
            return m++;
        } catch (InterruptedException e) {
            throw new RuntimeException("ERROR");
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        Thread[] th = new Thread[20];
        for (int i = 0; i < 20; i++) {
            th[i] = new Thread(() -> {
                System.out.println(demo.next());
            });
            th[i].start();
        }
    }

}
