package com.template.springproject.services;

import com.template.springproject.config.ApplicationProperties;
import com.template.springproject.model.User;
import com.template.springproject.util.exception.ReactiveApiException;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRequestSenderService {

    private final RestTemplate restTemplate;
    private final ApplicationProperties properties;


    public Observable<User> getUsers() {
        String url = properties.getDomain()+"/users-response";
        ResponseEntity<List<User>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        return Observable.fromIterable(Objects.requireNonNullElse(response.getBody(), new ArrayList<>()));

    }


    public Single<User> getUser (Integer userId) {
        String url = properties.getDomain()+"/users-response/" + userId;
        return Single.just(restTemplate.getForObject(url, User.class))
                .onErrorResumeNext(throwable -> Single.error(ReactiveApiException.ErrorHandler(throwable)));
    }


    public Single<User> createUser(User user) {
        String url = properties.getDomain()+"/users-response";
        HttpEntity<User> entity = new HttpEntity<>(user, null);
        ResponseEntity<User> response = restTemplate.postForEntity(URI.create(url), entity, User.class);
        return Single.just(response.getBody())
                    .onErrorResumeNext(throwable -> Single.error(ReactiveApiException.ErrorHandler(throwable)));
    }


    public Single<User> updateUser(User user) {
        String url = properties.getDomain()+"/users-response";
        HttpEntity<User> entity = new HttpEntity<>(user, null);
        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.PUT, entity, User.class);
        return Single.just(response.getBody())
                .onErrorResumeNext(throwable -> Single.error(ReactiveApiException.ErrorHandler(throwable)));
    }


    public Single<User> patchUser(User user) {
        String url = properties.getDomain()+"/users-response";
        HttpEntity<User> entity = new HttpEntity<>(user, null);
        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.PATCH, entity, User.class);
        return Single.just(response.getBody())
                .onErrorResumeNext(throwable -> Single.error(ReactiveApiException.ErrorHandler(throwable)));
    }


    public Completable deleteUser(Integer userId) {
        String url = properties.getDomain()+"/users-response/" + userId;
        return Completable.fromAction(() -> restTemplate.delete(url))
                .onErrorResumeNext(throwable -> Completable.error(ReactiveApiException.ErrorHandler(throwable)));
    }

}
