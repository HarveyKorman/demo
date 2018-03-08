package com.workflow.service;

import com.workflow.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
 
    public Flux<User> list();
 
    Flux<User> getById(final Flux<String> ids);
    
    Mono<User> getById(final String id);
 
//    Mono<User> getById(final String id) {
//        return Mono.justOrEmpty(this.data.get(id))
//                .switchIfEmpty(Mono.error(new ResourceNotFoundException()));
//    }
 
    Flux<User> createOrUpdate(final Flux<User> users);
 
    Mono<User> createOrUpdate(final User user);
 
    Mono<User> delete(final String id);
}
