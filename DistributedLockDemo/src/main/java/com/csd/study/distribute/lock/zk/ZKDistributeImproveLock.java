package com.csd.study.distribute.lock.zk;

import com.csd.study.distribute.lock.MyZkSerializer;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 利用临时顺序节点来实现分布式锁
 * 获取锁：排队取号（创建自己的临时顺序节点），然后判断自己是否为最小号，如是，则获得锁；否则，注册前一节点的watcher，阻塞等待
 * 释放锁：删除自己创建的临时顺序节点
 */
public class ZKDistributeImproveLock implements Lock {

    private String lockPath;

    private ZkClient client;

    private String currentPath;

    private String beforePath;

    public ZKDistributeImproveLock(String lockPath) {
        super();
        this.lockPath = lockPath;
        client = new ZkClient("localhost:2181");
        client.setZkSerializer(new MyZkSerializer());

        if (!this.client.exists(lockPath)) {
            try {
                this.client.createPersistent(lockPath);
            }catch (ZkNodeExistsException e){

            }

        }
    }

    @Override
    public void lock() {
        // 如果获取不到锁，阻塞等待
        if (!tryLock()) {
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

        client.subscribeDataChanges(this.beforePath, listener);

        // 阻塞自己
        if (this.client.exists(this.beforePath)) {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 取消注册
            client.unsubscribeDataChanges(this.beforePath, listener);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        if (this.currentPath == null){
            currentPath = this.client.createEphemeralSequential(lockPath + "/", "aaa");
        }

        // 获得所有子节点
        List<String> children = this.client.getChildren(lockPath);

        // 排序
        Collections.sort(children);

        if (currentPath.equals(lockPath + "/" + children.get(0))){
            return true;
        }else {
            // 取到前一个
            // 得到字节的索引号
            int curIndex = children.indexOf(currentPath.substring(lockPath.length() + 1));
            beforePath = lockPath + "/" + children.get(curIndex - 1);
        }

        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        /// 创建节点
        try {
            client.createEphemeral(lockPath);
        } catch (ZkNodeExistsException e) {
            return false;
        }
        return true;
    }

    @Override
    public void unlock() {
        client.delete(this.currentPath);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
