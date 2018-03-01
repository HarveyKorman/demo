package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/ws")
public class WsController {
    @RequestMapping(value="/send", method= RequestMethod.GET)
    public Mono<Void> getHello() {
        WebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute("ws://localhost:8080/echo", session -> {session.send(session.textMessage("ECHO TEST"));}).blockMillis(5000);
      
    }
}