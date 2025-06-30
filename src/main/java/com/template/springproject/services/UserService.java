package com.template.springproject.services;

import com.template.springproject.model.User;
import com.template.springproject.util.MemoryDataBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final MemoryDataBase memoryDataBase;

    public Flux<User> getUsers() {
        return Flux.fromIterable(memoryDataBase.findAll());
    }


    public Mono<User> getUser (Integer userId) {
        return Mono.just(memoryDataBase.findById(userId));
    }


    public Mono<User> createUser(User user) {
        return Mono.just(memoryDataBase.save(user));
    }


    public Mono<User> updateUser(User user) {
        return Mono.just(memoryDataBase.update(user));
    }


    public Mono<User> patchUser(User user) {

        return getUser(user.getId())
                .map(userFromDB -> fillUser(userFromDB, user))
                .flatMap(this::updateUser);
    }


    public Mono<Void> deleteUser(Integer userId) {
        return Mono.fromRunnable(() -> memoryDataBase.deleteById(userId));
    }


    private User fillUser(User userFromDB, User userUpdated){
        Optional.ofNullable(userUpdated.getName()).ifPresent(userFromDB::setName);
        Optional.ofNullable(userUpdated.getEmail()).ifPresent(userFromDB::setEmail);
        return userFromDB;
    }
}
