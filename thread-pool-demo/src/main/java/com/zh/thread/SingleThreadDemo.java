package com.zh.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadDemo {

    public static void main(String[] args) {
        // 创建单线程线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();
        // 创建10个任务给pool
        for(int i = 0; i < 10; i++){
            Runnable task = new TaskDemo();
            // 把任务交给pool去执行
            pool.execute(task);
        }

    }

}
