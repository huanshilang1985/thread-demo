package com.zh.thread;

/**
 * 线程池的各种状态
 */
public class StatusDemo {

    public static void main(String[] args) {
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY   = (1 << COUNT_BITS) - 1;

        // runState is stored in the high-order bits
        int RUNNING    = -1 << COUNT_BITS;
        int SHUTDOWN   =  0 << COUNT_BITS;
        int STOP       =  1 << COUNT_BITS;
        int TIDYING    =  2 << COUNT_BITS;
        int TERMINATED =  3 << COUNT_BITS;

        System.out.println("COUNT_BITS==" + COUNT_BITS + ", " + Integer.toBinaryString(COUNT_BITS));
        System.out.println("CAPACITY==" + CAPACITY + ", " + Integer.toBinaryString(CAPACITY));
        System.out.println("RUNNING==" + RUNNING + ", " + Integer.toBinaryString(RUNNING));
        System.out.println("SHUTDOWN==" + SHUTDOWN + ", " + Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP==" + STOP + ", " + Integer.toBinaryString(STOP));
        System.out.println("TIDYING==" + TIDYING + ", " + Integer.toBinaryString(TIDYING));
        System.out.println("TERMINATED==" + TERMINATED + ", " + Integer.toBinaryString(TERMINATED));
    }
}
