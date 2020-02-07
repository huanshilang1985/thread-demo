package com.zh.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private Helper helper = new Helper();

    /**
     * state=0时，表示可以使用。当state>=0时，表示已经被其他线程占用，
     * state并不表示线程数，因为arg是指定设置的值，并不一定是1
     */
    private class Helper extends AbstractQueuedSynchronizer{

        // 获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            // state=0表示可以获得锁
            if(state == 0){
                // 利用CAS原理修改state(当前值，期望修改的值)
                if(compareAndSetState(0, arg)){
                    // 设置当前线程占有资源
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else if (getExclusiveOwnerThread() == Thread.currentThread()){
                //可重入性设置，（判断当前占用资源的线程，是不是当前请求的线程，递增线程值）
                setState(getState() + arg);
                return true;
            }
            return false;
        }

        // 释放锁，arg表示要设置的值，应于获取锁的arg一致，state有可能是负数
        @Override
        protected boolean tryRelease(int arg) {
            int state = getState() - arg;
            // false表示没有释放成功
            // 判断释放后，state是否为0，这时可以被其他线程使用
            if(state == 0){
                setExclusiveOwnerThread(null);
                setState(state);
                return true;
            }
            // 下面的操作没有线程安全，因为在释放时是独占线程的。重复释放是重入性的问题
            setState(state);
            return false;
        }

        public Condition newConditionObject(){
            return new ConditionObject();
        }
    }

    /**
     * 获取锁
     */
    @Override
    public void lock() {
        //使用helper的acquire，使用独占式获取锁，信号量1
        helper.acquire(1);
    }

    /**
     * 锁可中断
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    /**
     * 尝试获取锁
     * @return
     */
    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    /**
     * 自旋获取锁的时间
     * @param time  超时时间
     * @param unit  时间单位
     * @return
     * @throws InterruptedException 如果在规定时间内没获取到锁，就会抛出异常
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        helper.release(1);
    }

    /**
     * 条件对象
     * @return
     */
    @Override
    public Condition newCondition() {
        return helper.newConditionObject();
    }
}
