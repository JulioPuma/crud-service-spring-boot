package com.template.springproject.services;

import com.template.springproject.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUser(){
        return User.builder()
                .name("Nombre")
                .email("nombre@gmail.com")
                .build();
    }
}
