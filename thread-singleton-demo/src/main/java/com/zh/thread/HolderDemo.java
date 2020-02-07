package com.zh.thread;

/**
 * 持有者模式
 * 在类初始化时，并没有对类进行初始化，是在调用时才创建；
 * 实现了懒加载，没有使用synchronized，所以没有锁，效率更高。
 * 加载过程中会放到init方法里，是一个同步方法，保证了唯一性（注：了解下static加载过程）
 * 这是应用比较广泛的单例模式
 */
public class HolderDemo {

    private HolderDemo() {

    }

    private static class Holder {
        private static HolderDemo instance = new HolderDemo();
    }

    public static HolderDemo getInstance() {
        return Holder.instance;
    }


}
