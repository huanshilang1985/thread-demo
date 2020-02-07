package com.zh.thread;

import java.util.concurrent.TimeUnit;

public class ReaderAndUpdater {

    static final int MAX = 5;
    static volatile int init_value = 1;

    public static void main(String[] args) {

        // 发现数据被修改，读取并打印
        new Thread(()->{
           int localValue = init_value;
            System.out.println("Reader");
           while (localValue< MAX){
               if(localValue != init_value) {
                   System.out.printf("The init_value is update to [%d] \n", init_value);
                   localValue = init_value;
               }
           }
        }, "Reader").start();

        // 修改数据进程
        new Thread(()->{
            int localValue = init_value;
            while (localValue < MAX){
                System.out.println("updater :" + (++localValue));
                init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }

}
