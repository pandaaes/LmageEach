package com.example.lmageeach.ws;

import com.example.lmageeach.config.GetHttpSessionConfig;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/chat",configurator = GetHttpSessionConfig.class)
public class ChatEndpoint {

    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();

    private HttpSession httpSession;


    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        this.httpSession =(HttpSession)config.getUserProperties().get(HttpSession.class.getName());
        String token =(String)this.httpSession.getAttribute("token");
        onlineUsers.put(token,session);
    }

    @OnMessage
    public void onMessage(String message){

    }

    @OnClose
    public void onClose(Session session){

    }
}
