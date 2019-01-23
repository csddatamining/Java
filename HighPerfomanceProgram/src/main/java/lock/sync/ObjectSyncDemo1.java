package main.java.lock.sync;

/**
 * @author Cdu
 * @discription: 锁 方法（静态/非静态），代码块（对象/类）
 * @create 2019-01-13 12:27
 */
public class ObjectSyncDemo1 {
    static Object temp = new Object();

    public void test1() {
        synchronized (ObjectSyncDemo1.class) {
            try {
                System.out.println(Thread.currentThread() + " 我开始执行");
                Thread.sleep(3000L);
                System.out.println(Thread.currentThread() + " 我执行结束");
            } catch (InterruptedException e) {
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            new ObjectSyncDemo1().test1();
        }).start();


        // 等1秒钟,让前一个线程启动起来
        Thread.sleep(1000L);
        new Thread(() -> {
            new ObjectSyncDemo1().test1();
        }).start();
    }

}