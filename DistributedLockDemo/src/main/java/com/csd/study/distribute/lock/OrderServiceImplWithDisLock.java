package com.csd.study.distribute.lock;

import com.csd.study.distribute.lock.zk.ZKDistributeImproveLock;

import java.util.concurrent.locks.Lock;

public class OrderServiceImplWithDisLock implements OrderService {

    // 用static修饰来模拟共用一个订单编号服务
    private static OrderCodeGenerator ocg = new OrderCodeGenerator();

    @Override
    public void createOrder() {
        String orderCode = null;

        // 分布式锁
        Lock lock = new ZKDistributeImproveLock("/csd");
        try {
            lock.lock();
            //获取订单号
            orderCode = ocg.getOrderCode();

        }finally {
            lock.unlock();
        }

        System.out.println(Thread.currentThread().getName() + "====>" + orderCode);

        // TODO: 2018/11/25 业务代码
    }
}
