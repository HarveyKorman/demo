package com.workflow.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workflow.User;
import com.workflow.mapper.UserMapper;
import com.workflow.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
    private final Map<String, User> data = new ConcurrentHashMap<>();
 
    public Flux<User> list() {
        return Flux.fromIterable(this.data.values());
    }
 
    public Flux<User> getById(final Flux<String> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(this.data.get(id)));
    }
    
    public Mono<User> getById(final String id) {
      return Mono.justOrEmpty(this.data.get(id));
  }
 
//    Mono<User> getById(final String id) {
//        return Mono.justOrEmpty(this.data.get(id))
//                .switchIfEmpty(Mono.error(new ResourceNotFoundException()));
//    }
// 
//    public Flux<User> createOrUpdate(final Flux<User> users) {
//        return users.doOnNext(user -> this.data.put(user.getUserId(), user));
//    }
 
    public Mono<User> createOrUpdate(final User user) {
    	userMapper.insert(user);
        return Mono.just(user);
    }
 
    public Mono<User> delete(final String id) {
        return Mono.justOrEmpty(this.data.remove(id));
    }

	@Override
	public Flux<User> createOrUpdate(Flux<User> users) {
		// TODO Auto-generated method stub
		return null;
	}
}
