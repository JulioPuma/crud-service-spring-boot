package com.template.springproject.util.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException{

    private String code;
    private String message;

    public ApiException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
