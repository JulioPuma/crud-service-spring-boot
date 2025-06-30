package com.template.springproject.controllers;

import com.template.springproject.model.User;
import com.template.springproject.services.UserRequestSenderService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-request")
@RequiredArgsConstructor
@Slf4j
public class UserRequestController {

    private final UserRequestSenderService userRequestSenderService;

    @GetMapping("")
    public Observable<User> getUsers() {
        log.info("UserRequestController - getUsers method");
        return userRequestSenderService.getUsers();
    }

    @GetMapping("/{userId}")
    public Single<User> getUser (
            @PathVariable Integer userId) {
        log.info("UserRequestController - getUser method");
        return userRequestSenderService.getUser(userId);
    }

    @PostMapping("")
    public Single<User> createUser(
            @RequestBody User user) {
        log.info("UserRequestController - createUser method");
        return userRequestSenderService.createUser(user);
    }

    @PutMapping("")
    public Single<User> updateUser(
            @RequestBody User user) {
        log.info("UserRequestController - updateUser method");
        return userRequestSenderService.updateUser(user);
    }

    @PatchMapping("")
    public Single<User> patchUser(
            @RequestBody User user) {
        log.info("UserRequestController - patchUser method");
        return userRequestSenderService.patchUser(user);
    }

    @DeleteMapping("/{userId}")
    public Completable deleteUser(
            @PathVariable Integer userId
    ) {
        log.info("UserRequestController - deleteUser method");
        return userRequestSenderService.deleteUser(userId);
    }
}
