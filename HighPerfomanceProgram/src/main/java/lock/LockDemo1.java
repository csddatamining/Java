package main.java.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Cdu
 * @discription: 利用底层API实现原子性操作的保证
 * @create 2019-01-12 14:21
 */
public class LockDemo1 {
    volatile int i = 0;

    static Unsafe unsafe;//直接操作内存，修改对象，数组内存

    static {
        try {
            //反射技术获取unsafe值
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

        } catch (Exception e) {

        }
    }

    public void add() {
        i++;
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