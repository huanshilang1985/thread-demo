package com.zh.thread;

/**
 * 枚举类型的单例模式
 */
public class EnumSingletonDemo {

    private EnumSingletonDemo(){

    }

    //延迟加载
    private enum EnumHolder{
        INSTANCE;

        private EnumSingletonDemo instance;

        EnumHolder(){
            this.instance = new EnumSingletonDemo();
        }

        private EnumSingletonDemo getInstance(){
            return instance;
        }
    }

    // 懒加载
    public static EnumSingletonDemo getInstance() {
        return EnumHolder.INSTANCE.getInstance();
    }
}
