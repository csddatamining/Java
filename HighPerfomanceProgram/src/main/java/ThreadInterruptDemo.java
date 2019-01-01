package main.java;

/**
 * @author Cdu
 * @discription: 线程终止Demo, 线程stop强制性中止，破坏线程安全的示例
 * @create 2019-01-01 23:39
 */
public class ThreadInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        StopThread thread = new StopThread();
        thread.start();
        // 休眠1秒，确保i变量自增成功
        Thread.sleep(1000);
        // 暂停线程
        //  thread.stop(); // 错误的终止
        thread.interrupt(); // 正确终止
        while (thread.isAlive()) {
            // 确保线程已经终止
        } // 输出结果
        thread.print();
    }

    static class StopThread extends Thread {
        private int i = 0, j = 0;

        @Override
        public void run() {
            synchronized (this) {
                // 增加同步锁，确保线程安全
                ++i;
                try {
                    // 休眠10秒,模拟耗时操作
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++j;
            }
        }

        /**
         * 打印i和j
         */
        public void print() {
            System.out.println("i=" + i + " j=" + j);
        }
    }
}