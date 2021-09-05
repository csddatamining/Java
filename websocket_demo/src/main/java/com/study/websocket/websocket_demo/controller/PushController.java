package com.study.websocket.websocket_demo.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Cdu
 * @discription: WebSocket for Server
 * @create 2021-04-06 23:50
 */
@Component
@ServerEndpoint(value = "/serverForWebSocket/{id}")
public class PushController {

    private Session session;

    private static CopyOnWriteArraySet<Session> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(session);
        System.out.println("Current connect user number:" + webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String msg, Session session) {
        System.out.println(msg);
        try {
            sendMessage(msg, session);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg, Session session) throws IOException {
        for (Session session1 : webSocketSet) {
            session1.getBasicRemote().sendText(msg);
        }
    }
}