package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/ws")
public class WsController {
    @RequestMapping(value="/send", method= RequestMethod.GET)
    public void getHello() {
    	final User user = new User();
        user.setId("Test");
    	final WebClient client = WebClient.create("ws://localhost:8080/echo");
    	client.get();
//    	final Mono<User> createdUser = client.get()
//                .uri("")
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .flatMap(response -> response.bodyToMono(User.class));
//        System.out.println(createdUser.block());
      
    }
}