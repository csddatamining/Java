package main.java.concurrent.thread.lock.cas;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Cdu
 * @date 2019-01-29 下午2:04
 */
public class LongAdderDemo {
    private long count = 0;

    // 同步代码块的方式
    public void testSync() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                long starttime = System.currentTimeMillis();
                while (System.currentTimeMillis() - starttime < 1000) { // 运行一秒
                    synchronized (this) {
                        ++count;
                    }
                }
                long endtime = System.currentTimeMillis();
                System.out.println("SyncThread spend:" + (endtime - starttime) + "ms" + "  count:" + count);
            }).start();
        }
    }

    // Atomic方式
    private AtomicLong acount = new AtomicLong(0L);

    public void testAtomic() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                long starttime = System.currentTimeMillis();
                while (System.currentTimeMillis() - starttime < 1000) { // 运行一秒
                    acount.incrementAndGet(); // acount++;
                }
                long endtime = System.currentTimeMillis();
                System.out.println("AtomicThread spend:" + (endtime - starttime) + "ms" + " count:" + acount.incrementAndGet());
            }).start();
        }
    }

    // LongAdder 方式
    private LongAdder lacount = new LongAdder();

    public void testLongAdder() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                long starttime = System.currentTimeMillis();
                while (System.currentTimeMillis() - starttime < 1000) { // 运行一秒
                    lacount.increment();
                }
                long endtime = System.currentTimeMillis();
                System.out.println("LongAdderThread spend:" + (endtime - starttime) + "ms" + " count:" + lacount.sum());
            }).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LongAdderDemo demo = new LongAdderDemo();
        demo.testSync();
        demo.testAtomic();
        demo.testLongAdder();
    }
}
