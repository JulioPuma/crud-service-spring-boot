package com.template.springproject.controllers;

import com.template.springproject.model.User;
import com.template.springproject.services.UserResponseService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-response")
@RequiredArgsConstructor
@Slf4j
public class UserResponseController {

    private final UserResponseService userResponseService;

    @GetMapping("")
    public Observable<User> getUsers() {
        log.info("UserResponseController - getUsers method");
        return userResponseService.getUsers();
    }

    @GetMapping("/{userId}")
    public Single<User> getUser (
            @PathVariable Integer userId) {
        log.info("UserResponseController - getUser method");
        return userResponseService.getUser(userId);
    }

    @PostMapping("")
    public Single<User> createUser(
            @RequestBody User user) {
        log.info("UserResponseController - createUser method");
        return userResponseService.createUser(user);
    }

    @PutMapping("")
    public Single<User> updateUser(
            @RequestBody User user) {
        log.info("UserResponseController - updateUser method");
        return userResponseService.updateUser(user);
    }

    @PatchMapping("")
    public Single<User> patchUser(
            @RequestBody User user) {
        log.info("UserResponseController - patchUser method");
        return userResponseService.patchUser(user);
    }

    @DeleteMapping("/{userId}")
    public Completable deleteUser(
            @PathVariable Integer userId) {
        log.info("UserResponseController - deleteUser method");
        return userResponseService.deleteUser(userId);
    }
}
