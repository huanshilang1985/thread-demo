package com.zh.thread;

/**
 * 双重锁（double-checked-locking，简称DCL）
 * 使用volatile和synchronized设置了双重锁
 */
public class Singleton {

    private volatile static Singleton instance;

    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                instance = new Singleton();
            }
        }
        return instance;
    }

}
