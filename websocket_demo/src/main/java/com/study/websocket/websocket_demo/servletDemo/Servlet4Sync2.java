package com.study.websocket.websocket_demo.servletDemo;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Cdu
 * @discription:
 * @create 2021-04-19 23:48
 */
@WebServlet(value = "/test1", asyncSupported = true)
public class Servlet4Sync2 extends HttpServlet {

    private static ThreadPoolExecutor executor =
            new ThreadPoolExecutor(100,200,5000L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(200));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();
        System.out.println("Current thread:" + Thread.currentThread().getName());
        executor.execute(()->{
            new DoSomethingRunning().run();
            try {
                asyncContext.getResponse().getWriter().write("ok, get a response");
            } catch (IOException e) {
                e.printStackTrace();
            }
            asyncContext.complete();
        });
//        asyncContext.start(()->{
//            new DoSomethingRunning().run();
//            try {
//                asyncContext.getResponse().getWriter().write("ok, get a response");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            asyncContext.complete();
//        });
    }
}