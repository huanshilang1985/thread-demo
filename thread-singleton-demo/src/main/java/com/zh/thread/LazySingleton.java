package com.zh.thread;

/**
 * 懒汉式-单例模式
 * 相对于饿汉式，懒汉式是用到才加载，性能是比较好的
 */
public class LazySingleton {

    private static LazySingleton instance = null;

    private LazySingleton(){

    }

    /**
     * 此方法是线程安全的，但会把多线程退化成串行执行，不建议使用
     */
    public synchronized static LazySingleton getInstance(){
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }

    /**
     * 此方法不是线程安全的
     * 虽然使用了synchronized关键字，并减少了加锁粒度
     * 如果2个线程同时要创建此对象，线程1判断=null获得锁，线程2判断=null等待锁，等线程1释放锁之后，线程2还会再次创建一次对象；
     */
    public static LazySingleton getInstance1(){
        if(null == instance){
            synchronized (LazySingleton.class){
                instance = new LazySingleton();
            }
        }
        return instance;
    }

    /**
     * 此方法是线程安全的
     * 采用DCL并发模式（Double-check-locking）两次检测加锁
     */
    public static LazySingleton getInstance2(){
        if(null == instance){
            synchronized (LazySingleton.class){
                if(null == instance){
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HungrySingleton.getInstance());
            }).start();
        }
    }

}
