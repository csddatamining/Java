package com.study.performance;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * JVM优化分析Demo
 *
 * @author Cdu
 * @date 2019-02-16 16:22
 */
@SpringBootApplication
public class JVMAnalyzeDemo {

    public static void main(String[] args) {
        SpringApplication.run(JVMAnalyzeDemo.class, args);
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    try {
                        //  不干活，专门512kb的小对象
                        byte[] temp = new byte[1024 * 512];
                        Thread.sleep(new Random().nextInt(100)); // 随机睡眠100毫秒秒以内
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }, 100, 100, TimeUnit.MILLISECONDS);
    }
}
