package com.template.springproject.controllers;

import com.template.springproject.model.User;
import com.template.springproject.services.UserService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public Observable<User> getUsers() {

        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public Single<User> getUser (
            @PathVariable Integer userId) {

        return userService.getUser(userId);
    }

    @PostMapping("")
    public Single<User> createUser(
            @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("")
    public Single<User> updateUser(
            @RequestBody User user) {
        return userService.updateUser(user);
    }

    @PatchMapping("")
    public Single<User> patchUser(
            @RequestBody User user) {
        return userService.patchUser(user);
    }

    @DeleteMapping("/{userId}")
    public Completable deleteUser(
            @PathVariable Integer userId
    ) {
        return userService.deleteUser(userId);
    }
}
