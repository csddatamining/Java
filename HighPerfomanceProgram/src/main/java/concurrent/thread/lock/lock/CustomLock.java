package main.java.concurrent.thread.lock.lock;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Shuaiduoooo
 * @discription: Custom Lock 实现独享锁
 * @create 2020-02-01 23:27
 */
public class CustomLock implements Lock {

    volatile AtomicReference<Thread> owner = new AtomicReference<>();

    volatile LinkedBlockingDeque<Thread> waiters = new LinkedBlockingDeque<>();

    @Override
    public boolean tryLock() {
        return owner.compareAndSet(null, Thread.currentThread());
    }

    @Override
    public void unlock() {

    }

    @Override
    public void lock() {
        if (!tryLock()){
            waiters.offer(Thread.currentThread());
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}