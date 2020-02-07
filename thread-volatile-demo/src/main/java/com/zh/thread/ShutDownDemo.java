package com.zh.thread;

/**
 * 自旋锁
 */
public class ShutDownDemo extends Thread {

    private volatile boolean started = false;

    @Override
    public void run(){
        // 自旋锁
        while (started) {
            //doWork();
        }
    }

    /**
     * 自旋锁一定要定义好退出条件
     */
    public void shutdown(){
        started = false;
    }

}
