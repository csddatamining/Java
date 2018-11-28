package com.csd.study.distribute.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class ZkWatcherDemo {
    public static void main(String[] args) {
        //创建zk客户端
        ZkClient client = new ZkClient("localhost:2181");
        client.setZkSerializer(new MyZkSerializer());

        client.subscribeDataChanges("csd/a", new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("====监听到节点数据变化：" + s + "====");
            }

            public void handleDataDeleted(String s) throws Exception {
                System.out.println("====监听到数据被删除=====");
            }
        });

        // sleep two minutes in case of operate and watch the result
        try {
            Thread.sleep(1000 * 60 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
