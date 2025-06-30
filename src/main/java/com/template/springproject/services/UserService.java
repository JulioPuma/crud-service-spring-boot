package com.template.springproject.services;

import com.template.springproject.model.Usuario;
import com.template.springproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public Flux<Usuario> getUsers() {
        return userRepository.findAll();
    }


    public Mono<Usuario> getUser (Integer userId) {
        return userRepository.findById(userId)
                .switchIfEmpty(Mono.error(new NoSuchElementException("No existe Usuario")));

    }

    public Mono<Usuario> createUser(Usuario usuario) {
        return userRepository.save(usuario);
    }


    public Mono<Usuario> updateUser(Usuario usuario) {
        return userRepository.save(usuario);
    }


    public Mono<Usuario> patchUser(Usuario usuario) {

        return getUser(usuario.getId())
                .map(usuarioFromDB -> fillUser(usuarioFromDB, usuario))
                .flatMap(this::updateUser);
    }

    public Mono<Void> deleteUser(Integer userId) {
        return userRepository.deleteById(userId);
    }


    private Usuario fillUser(Usuario usuarioFromDB, Usuario usuarioUpdated){
        Optional.ofNullable(usuarioUpdated.getName()).ifPresent(usuarioFromDB::setName);
        Optional.ofNullable(usuarioUpdated.getEmail()).ifPresent(usuarioFromDB::setEmail);
        return usuarioFromDB;
    }
}
