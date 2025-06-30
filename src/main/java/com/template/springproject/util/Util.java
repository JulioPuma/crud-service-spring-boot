package com.template.springproject.util;

import com.template.springproject.model.User;

import java.util.Arrays;
import java.util.List;

public class Util {

    public static List<User> generateUsers(){
        return Arrays.asList(
                User.builder().id(1).name("Roberto").email("roberto@gmail.com").build(),
                User.builder().id(2).name("Fabrizio").email("fabrizio@gmail.com").build());
    }
}
