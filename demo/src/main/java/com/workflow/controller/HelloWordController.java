package com.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.workflow.User;
import com.workflow.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping("/user")
public class HelloWordController {
	@Autowired
    private UserService userService;
    
//    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public void notFound() {
//    }
 
    @GetMapping("")
    public Flux<User> list() {
        return this.userService.list();
    }
 
    @GetMapping("/{id}")
    public Mono<User>getById(@PathVariable("id") final String id) {
        return this.userService.getById(id);
    }
 
    @PostMapping("")
    public Flux<User> create(@RequestBody final Flux<User>  users) {
        return this.userService.createOrUpdate(users);
    }
 
//    @PutMapping("/{id}")
//    public Mono<User>  update(@PathVariable("id") final String id, @RequestBody final User user) {
//        Objects.requireNonNull(user);
//        user.setId(id);
//        return this.userService.createOrUpdate(user);
//    }
 
    @DeleteMapping("/{id}")
    public Mono<User>  delete(@PathVariable("id") final String id) {
        return this.userService.delete(id);
    }
    
  @RequestMapping(value="/hello", method= RequestMethod.GET)
  public Mono<String> getHello() {
	  User user = new User();
	  user.setUserId(new Integer("1"));
	  
	this.userService.createOrUpdate(user);
    return Mono.just("Hello World123");
    
  }
}
