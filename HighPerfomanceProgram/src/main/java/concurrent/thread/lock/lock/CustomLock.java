package main.java.concurrent.thread.lock.lock;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Shuaiduoooo
 * @discription: Custom Lock 实现独享锁
 * @create 2020-02-01 23:27
 */
public class CustomLock implements Lock {

    /**
     *
     */
    private volatile AtomicReference<Thread> owner = new AtomicReference<>();

    /**
     * 保存正在等待的线程
     */
    private volatile LinkedBlockingDeque<Thread> waiters = new LinkedBlockingDeque<>();

    @Override
    public boolean tryLock() {
        return owner.compareAndSet(null, Thread.currentThread());
    }

    @Override
    public void unlock() {
        //释放owner
        if (owner.compareAndSet(Thread.currentThread(), null)){
            //通知等待者
            Iterator<Thread> iterator = waiters.iterator();
            while (iterator.hasNext()){
                Thread next = iterator.next();
                // 唤醒
                LockSupport.unpark(next);
            }
        }
    }

    @Override
    public void lock() {
        boolean addQue = true;
        while (!tryLock()) {
            if (addQue) {
                //没拿到锁，加入到等待集合
                waiters.offer(Thread.currentThread());
                addQue = false;
            } else {
                //阻塞，挂起当前线程，不继续抢锁
                LockSupport.park();//注意伪唤醒，非unPark引起的唤醒
            }
        }
        waiters.remove(Thread.currentThread());
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