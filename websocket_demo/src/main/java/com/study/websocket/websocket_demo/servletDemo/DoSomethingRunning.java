package com.study.websocket.websocket_demo.servletDemo;

import java.util.Random;

/**
 * @author Cdu
 * @discription:
 * @create 2021-04-19 23:52
 */
public class DoSomethingRunning {
    public void run() {
        Random random = new Random();
        int time = random.nextInt(5000);
        String thread = Thread.currentThread().getName();
        System.out.println("Thread:" + thread + " start running");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}