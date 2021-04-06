package com.study.websocket.websocket_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Cdu
 * @discription: WebSocket Config Class
 * @create 2021-04-06 23:45
 */
@Configuration
public class WebSocketController {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}