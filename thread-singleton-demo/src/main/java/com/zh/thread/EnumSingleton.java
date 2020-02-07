package com.zh.thread;

/**
 * 保证了线程安全，但不能懒加载
 */
public enum EnumSingleton {

    INSTANCE;

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

}
