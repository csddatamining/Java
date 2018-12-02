package com.csd.study.distribute.lock.zk;

import com.csd.study.distribute.lock.MyZkSerializer;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ZKDistributeLock implements Lock {

    private String lockPath;

    private ZkClient client;

    public ZKDistributeLock(String lockPath) {
        super();
        this.lockPath = lockPath;

        client = new ZkClient("localhost:2181");
        client.setZkSerializer(new MyZkSerializer());
    }

    public void lock() {
        // 如果获取不到锁，阻塞等待
        if (!tryLock()){
            // 没有获得锁,阻塞自己
            waitForLock();
            // 再次尝试
            lock();
        }
    }

    private void waitForLock() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        // 注册节点失败的监听
        IZkDataListener listener = new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("====节点被删了=====");
                countDownLatch.countDown();
            }

            public void handleDataDeleted(String s) throws Exception {

            }
        };

        client.subscribeDataChanges(lockPath, listener);

        // 阻塞自己
        if (this.client.exists(lockPath)){
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 取消注册
            client.unsubscribeDataChanges(lockPath, listener);
        }
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        /// 创建节点
        try {
            client.createEphemeral(lockPath);
        } catch (ZkNodeExistsException e)
        {
            return false;
        }
        return true;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        client.delete(lockPath);
    }

    public Condition newCondition() {
        return null;
    }
}
