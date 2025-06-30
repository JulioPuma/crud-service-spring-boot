package com.template.springproject.controllers;

import com.template.springproject.model.Usuario;
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
    public Flux<Usuario> getUsers() {

        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public Mono<Usuario> getUser (
            @PathVariable Integer userId) {

        return userService.getUser(userId);
    }

    @PostMapping("")
    public Mono<Usuario> createUser(
            @RequestBody Usuario usuario) {
        return userService.createUser(usuario);
    }

    @PutMapping("")
    public Mono<Usuario> updateUser(
            @RequestBody Usuario usuario) {
        return userService.updateUser(usuario);
    }

    @PatchMapping("")
    public Mono<Usuario> patchUser(
            @RequestBody Usuario usuario) {
        return userService.patchUser(usuario);
    }

    @DeleteMapping("/{userId}")
    public Mono<Void> deleteUser(
            @PathVariable Integer userId
    ) {
        return userService.deleteUser(userId);
    }
}
