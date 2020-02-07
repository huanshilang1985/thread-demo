package com.zh.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁测试
 */
public class Demo03 {

    // 可重入锁
    private ReentrantLock lock = new ReentrantLock();
    private int m = 0;

    public void a(){
        lock.lock();
        System.out.println("a===");
        b();
        lock.unlock();
    }

    public void b(){
        lock.lock();
        System.out.println("b===");
        lock.unlock();
    }

    public int next() {
        lock.lock();
        try {
            return m++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Demo03 demo = new Demo03();
        new Thread(()->{
            demo.a();
        }).start();
    }

}
