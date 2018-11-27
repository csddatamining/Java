package com.csd.study.distribute.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ConcurrentTestDistributeDemo {
    public static void main(String[] args) {
        // 并发数
        int currency = 20;

        // 循环屏障
        CyclicBarrier cb = new CyclicBarrier(currency);
        for (int i = 0; i < currency; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 模拟分布式或集群的场景
                    OrderService orderService = new OrderServiceImplWithLock();

                    System.out.println(Thread.currentThread().getName()+ "=====准备好了");

                    try {
                        cb.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    // 调用创建订单服务
                    orderService.createOrder();
                }

            }).start();
        }
    }
}
