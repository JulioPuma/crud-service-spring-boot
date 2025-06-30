package com.template.springproject.util.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
public class ApiException extends Exception{

    private String code;
    private String description;
    private HttpStatus statusCode;
    private List<ApiExceptionDetail> apiExceptionDetails;

}
