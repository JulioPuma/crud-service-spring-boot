package com.template.springproject.services;

import com.template.springproject.model.User;
import com.template.springproject.util.MemoryDataBase;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final MemoryDataBase memoryDataBase;

    public Observable<User> getUsers() {
        return Observable.fromIterable(memoryDataBase.findAll());
    }


    public Single<User> getUser (Integer userId) {
        return Single.just(memoryDataBase.findById(userId));
    }


    public Single<User> createUser(User user) {
        return Single.just(memoryDataBase.save(user));
    }


    public Single<User> updateUser(User user) {
        return Single.just(memoryDataBase.update(user));
    }


    public Single<User> patchUser(User user) {

        return getUser(user.getId())
                .map(userFromDB -> fillUser(userFromDB, user))
                .flatMap(this::updateUser);
    }


    public Completable deleteUser(Integer userId) {
        return Completable.fromAction(() -> memoryDataBase.deleteById(userId));
    }


    private User fillUser(User userFromDB, User userUpdated){
        Optional.ofNullable(userUpdated.getName()).ifPresent(userFromDB::setName);
        Optional.ofNullable(userUpdated.getEmail()).ifPresent(userFromDB::setEmail);
        return userFromDB;
    }
}
