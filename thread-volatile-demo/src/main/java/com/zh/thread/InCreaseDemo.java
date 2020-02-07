package com.zh.thread;

/**
 * 程序结果，m小于等于50，且每次不太相同
 * 因为m++不是原子性，volatile不保证原子性，即使方法加上了synchronized关键字，也不能保证每个线程的执行顺序
 *
 */
public class InCreaseDemo {

    static volatile  int m = 0;

    public synchronized static void  increase(){
        m++;
    }

    public static void main(String[] args) {
        for(int i = 0; i< 10; i++){
            new Thread(() -> {
                for (int j = 0; j < 5; j++){
                    increase();
                }
            }).start();
        }
        System.out.println(m);
    }
}
