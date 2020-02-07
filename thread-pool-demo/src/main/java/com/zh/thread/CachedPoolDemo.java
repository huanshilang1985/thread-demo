package com.zh.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedPoolDemo {

    public static void main(String[] args) {
        // 创建可变大小的线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        // 创建10个任务给pool
        for(int i = 0; i < 10; i++){
            Runnable task = new TaskDemo();
            // 把任务交给pool去执行
            pool.execute(task);
        }
    }
}
