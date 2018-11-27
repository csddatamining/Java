package com.csd.study.distribute.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ConcurrentTestDemo {
    public static void main(String[] args) {
        // 并发数
        int currency = 50;

        //循环屏障
        CyclicBarrier cb = new CyclicBarrier(currency);

        OrderService orderService  = new OrderServiceImpl();

        for (int i = 0; i < currency; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+ "====准备好了=====");
                    try {
                        cb.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    //调用创建订单服务
                    orderService.createOrder();
                }
            }).start();
        }
    }
}
