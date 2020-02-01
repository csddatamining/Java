package main.java.concurrent.thread.lock.lock;

/**
 * @author Cdu
 * @discription: 多线程对变量进行递增操作
 * @create 2019-01-12 12:00
 */
public class LockDemo {

    volatile int i = 0;

    public void add() {
//        //增加synchronized关键字进行同步
//        synchronized (this) {
        i++;
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo lockDemo = new LockDemo();
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