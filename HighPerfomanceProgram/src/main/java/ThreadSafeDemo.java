package main.java;

import java.util.concurrent.TimeUnit;

/**R
 * 线程安全Demo
 * 通过设置JVM的参数，打印出jit编译的内容
 * -server -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:+LogCompilation -XX:LogFile=jit.log
 * 关闭jit优化：-Djava.compiler=NONE
 * @author Cdu
 * @date 2019-01-07 下午1:28
 */
public class ThreadSafeDemo {

    private volatile  boolean flag = true;

    public static void main(String[] args) throws InterruptedException{
        final ThreadSafeDemo threadSafeDemo = new ThreadSafeDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (threadSafeDemo.flag){
                    i++;
                }
                System.out.println(i);
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);
        threadSafeDemo.flag = false;
        System.out.println("置为false");

    }
}
