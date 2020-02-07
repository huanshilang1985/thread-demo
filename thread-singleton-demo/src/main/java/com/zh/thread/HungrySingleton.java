package com.zh.thread;

/**
 * 饿汉式-单例模式
 * 因项目加载时就实例化了对象到内存中，所以是线程安全的。
 * 但提前加载的问题在于，如果成员变量过多，或者使用不多的话，就造成内存浪费。
 */
public class HungrySingleton {

    /**
     * static修饰的，说明是加载时就把对象实例化到内存中
     */
    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {

    }

    /**
     * 返回实例对象
     *
     * @return
     */
    public static HungrySingleton getInstance() {
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
