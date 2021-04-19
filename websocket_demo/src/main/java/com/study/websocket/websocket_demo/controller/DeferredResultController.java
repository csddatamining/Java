package com.study.websocket.websocket_demo.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Cdu
 * @discription:
 * @create 2021-04-20 0:05
 */
@RestController
public class DeferredResultController {

    private LinkedBlockingDeque<DeferredResult> queue = new LinkedBlockingDeque<>();

    ThreadPoolExecutor executor = new ThreadPoolExecutor(1000, 1500, 5000L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200));

    @RequestMapping("/get")
    public DeferredResult<String> getResult() {
        final DeferredResult<String> stringDeferredResult = new DeferredResult<>(3000L);
        queue.add(stringDeferredResult);

        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Random random = new Random();
                    int time = random.nextInt(5000);
                    System.out.println("休眠时间："+ time);
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("处理业务");
                String rs = "返回值...";
                stringDeferredResult.setResult(rs);
            }
        });

        stringDeferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                System.out.println("超时处理");
                stringDeferredResult.setResult("超时返回结果");
            }
        });

        stringDeferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务完成，队列中的deferredResult移除");
            }
        });
        return stringDeferredResult;
    }

    @Scheduled(fixedRate = 1000)
    public void scheduleResult(){
        for (int i = 0; i < queue.size(); i++) {
            DeferredResult<String> stringDeferredResult = queue.poll();
            stringDeferredResult.setResult("result:" + i);
        }
    }
}