package com.study.websocket.websocket_demo.servletDemo;

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
 * @create 2021-04-09 22:27
 */
@WebServlet("/test")
public class Servlet4Sync1 extends HttpServlet {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 5000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(200));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Current Thread name:" + Thread.currentThread().getName());
        new DoSomethingRunning().run();
        resp.getWriter().write("OK, get a response");
    }
}