package main.java;

/**
 * @author Cdu
 * @discription: 多线程相关Demo
 * 线程状态
 * java.lang.Thread.State
 * 1.New：新建线程尚未启动的线程状态
 * 2.Runnable可运行线程的线程状态，等待CPU调度
 * 3.Blocked：线程阻塞等待监视器锁定的线程状态
 * 处于synchronized同步代码块或方法中被阻塞
 * 4.Waiting：等待线程的线程状态
 * 5.Timed Waiting：具有指定等待时间的等待线程的线程状态
 * 6.Terminated：终止线程的线程状态。线程正常完成执行或者出现异常
 * @create 2018-12-30 17:18
 */
public class ThreadStateDemo {
    public static Thread thread1;
    public static ThreadStateDemo obj;

    public static void main(String[] args) throws Exception {
        // 第一种状态切换 - 新建 -> 运行 -> 终止
        System.out.println("#######第一种状态切换  - 新建 -> 运行 -> 终止################################");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1当前状态：" + Thread.currentThread().getState().toString());
                System.out.println("thread1 执行了");
            }
        });
        System.out.println("没调用start方法，thread1当前状态：" + thread1.getState().toString());
        thread1.start();
        Thread.sleep(2000L); // 等待thread1执行结束，再看状态
        System.out.println("等待两秒，再看thread1当前状态：" + thread1.getState().toString());
        // thread1.start(); TODO 注意，线程终止之后，再进行调用，会抛出IllegalThreadStateException异常

        System.out.println();
        System.out.println("############第二种：新建 -> 运行 -> 等待 -> 运行 -> 终止(sleep方式)###########################");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {// 将线程2移动到等待状态，1500后自动唤醒
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2当前状态：" + Thread.currentThread().getState().toString());
                System.out.println("thread2 执行了");
            }
        });
        System.out.println("没调用start方法，thread2当前状态：" + thread2.getState().toString());
        thread2.start();
        System.out.println("调用start方法，thread2当前状态：" + thread2.getState().toString());
        Thread.sleep(200L); // 等待200毫秒，再看状态
        System.out.println("等待200毫秒，再看thread2当前状态：" + thread2.getState().toString());
        Thread.sleep(3000L); // 再等待3秒，让thread2执行完毕，再看状态
        System.out.println("等待3秒，再看thread2当前状态：" + thread2.getState().toString());

        System.out.println();
        System.out.println("############第三种：新建 -> 运行 -> 阻塞 -> 运行 -> 终止###########################");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ThreadStateDemo.class) {
                    System.out.println("thread3当前状态：" + Thread.currentThread().getState().toString());
                    System.out.println("thread3 执行了");
                }
            }
        });
        synchronized (ThreadStateDemo.class) {
            System.out.println("没调用start方法，thread3当前状态：" + thread3.getState().toString());
            thread3.start();
            System.out.println("调用start方法，thread3当前状态：" + thread3.getState().toString());
            Thread.sleep(200L); // 等待200毫秒，再看状态
            System.out.println("等待200毫秒，再看thread3当前状态：" + thread3.getState().toString());
        }
        Thread.sleep(3000L); // 再等待3秒，让thread3执行完毕，再看状态
        System.out.println("等待3秒，让thread3抢到锁，再看thread3当前状态：" + thread3.getState().toString());

    }
}