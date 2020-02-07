package com.zh.thread;

/**
 * 加锁测试，测试结果没有重复值
 * 但线程有快有慢，所以显示顺序不能保证
 */
public class Demo01 {

    private MyLock lock = new MyLock();
    private int m = 0;

    public int next() {
        lock.lock();
        try {
            return m++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Demo01 demo = new Demo01();
        Thread[] th = new Thread[20];
        for (int i = 0; i < 20; i++) {
            th[i] = new Thread(() -> {
                System.out.println(demo.next());
            });
            th[i].start();
        }
    }

}
