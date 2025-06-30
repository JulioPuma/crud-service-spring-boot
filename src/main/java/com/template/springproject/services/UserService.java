package com.template.springproject.services;

import com.template.springproject.model.User;
import com.template.springproject.repository.UserRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public Observable<User> getUsers() {
        return Observable.fromIterable(userRepository.findAll());
    }


    public Single<User> getUser (Integer userId) {
        return Single.just(
                userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("No existe usuario.")));
    }


    public Single<User> createUser(User user) {
        return Single.just(userRepository.save(user));
    }


    public Single<User> updateUser(User user) {
        return Single.just(userRepository.save(user));
    }


    public Single<User> patchUser(User user) {

        return getUser(user.getId())
                .map(userFromDB -> fillUser(userFromDB, user))
                .flatMap(this::updateUser);
    }


    public Completable deleteUser(Integer userId) {
        return Completable.fromAction(() -> userRepository.deleteById(userId));
    }


    private User fillUser(User userFromDB, User userUpdated){
        Optional.ofNullable(userUpdated.getName()).ifPresent(userFromDB::setName);
        Optional.ofNullable(userUpdated.getEmail()).ifPresent(userFromDB::setEmail);
        return userFromDB;
    }
}
