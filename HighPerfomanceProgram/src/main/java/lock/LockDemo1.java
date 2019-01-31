package main.java.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Cdu
 * @discription: 利用底层API实现原子性操作的保证
 * @create 2019-01-12 14:21
 */
public class LockDemo1 {

    volatile int value = 0;

    /**
     * 直接操作内存，修改对象，数组内存
     */
    static Unsafe unsafe;

    private static long valueOffset;

    static {
        try {
            //反射技术获取unsafe值
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            //获取到value属性偏移量，用于定位value属性在内存中的具体地址
            valueOffset = unsafe.objectFieldOffset(LockDemo1.class.getDeclaredField("value"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add() {
        // CAS+循环重试
        int current;
        do {
            //操作耗时的话， 线程会占用大量的CPU执行时间
            current = unsafe.getIntVolatile(this, valueOffset);
        } while (!unsafe.compareAndSwapInt(this, valueOffset, current, current + 1));

    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo1 lockDemo1 = new LockDemo1();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    lockDemo1.add();
                }
            }).start();
        }

        Thread.sleep(2000L);
        System.out.println(lockDemo1.value);
    }
}