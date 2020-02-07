package com.zh.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledPoolDemo {

    public static void main(String[] args) {
        // 创建一个可调度的线程池（会根据任务执行时间随机调度）
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        // 创建10个任务给pool
        for(int i = 0; i < 10; i++){
            Runnable task = new TaskDemo();
            // 把任务交给pool去执行
            pool.execute(task);
        }
    }

}
