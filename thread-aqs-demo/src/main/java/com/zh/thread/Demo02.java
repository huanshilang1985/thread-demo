package com.zh.thread;

/**
 * 双重加锁测试
 * 因为a和b方法使用同一个锁，锁是不能冲入的，所以只输出a之后，lock返回false，b就不会执行，会一直等待a释放锁。
 * 如果锁不是可重入锁，会一直等待，造成死锁。
 * 如果是可重入锁，就两条记录都会输出。
 */
public class Demo02 {

    private MyLock lock = new MyLock();
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
        Demo02 demo = new Demo02();
        new Thread(()->{
            demo.a();
        }).start();
    }

}
