package com.template.springproject.controllers;

import com.template.springproject.model.User;
import com.template.springproject.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public Flux<User> getUsers() {

        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public Mono<User> getUser (
            @PathVariable Integer userId) {

        return userService.getUser(userId);
    }

    @PostMapping("")
    public Mono<User> createUser(
            @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("")
    public Mono<User> updateUser(
            @RequestBody User user) {
        return userService.updateUser(user);
    }

    @PatchMapping("")
    public Mono<User> patchUser(
            @RequestBody User user) {
        return userService.patchUser(user);
    }

    @DeleteMapping("/{userId}")
    public Mono<Void> deleteUser(
            @PathVariable Integer userId
    ) {
        return userService.deleteUser(userId);
    }
}
