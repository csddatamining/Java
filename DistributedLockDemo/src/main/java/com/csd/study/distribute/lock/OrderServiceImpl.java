package com.csd.study.distribute.lock;

public class OrderServiceImpl implements OrderService {

    private OrderCodeGenerator ocg = new OrderCodeGenerator();

    @Override
    public void createOrder() {
        // 获取订单号
        String orderCode = ocg.getOrderCode();

        System.out.println(Thread.currentThread().getName() + "====>" + orderCode);

    }

}
