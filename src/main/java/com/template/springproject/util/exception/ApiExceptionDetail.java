package com.template.springproject.util.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiExceptionDetail {
    private String code;
    private String component;
    private String message;
}
