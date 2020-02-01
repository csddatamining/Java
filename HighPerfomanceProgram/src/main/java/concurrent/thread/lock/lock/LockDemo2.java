package main.java.concurrent.thread.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用Atomic进行原子操作
 *
 * @author Shuaiduoooo
 * @date 2019-01-29 11:31
 */
public class  LockDemo2 {

    AtomicInteger i = new AtomicInteger(0);

    public void add(){
        i.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo2 lockDemo = new LockDemo2();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    lockDemo.add();
                }
            }).start();
        }

        Thread.sleep(2000L);
        System.out.println(lockDemo.i);
    }
}
