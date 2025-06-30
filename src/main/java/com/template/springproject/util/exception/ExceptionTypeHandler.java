package com.template.springproject.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Getter
public enum ExceptionTypeHandler {
    ERR000("ERR000", Exception.class, "Error general", HttpStatus.INTERNAL_SERVER_ERROR),
    ERR001("ERR001", NoSuchElementException.class, "No existe elemento a consultar", HttpStatus.NOT_FOUND);

    private final String code;
    private final Class<? extends Exception> exceptionClass;
    private final String description;
    private final HttpStatus status;

    private static final String appName = "api-client-service-rest-template";

    public ApiException buildApiException(){
    return buildApiException(new Exception());
    }

    public static ApiException buildApiException(Throwable exception){
    return ApiException.builder()
            .code(ERR000.code)
            .description(ERR000.description)
            .statusCode(ERR000.status)
            .apiExceptionDetails(Arrays.asList(
                ApiExceptionDetail.builder()
                .component(appName)
                .message(exception.getMessage())
                .build()))
            .build();
    }
    public static ApiException buildApiException(ExceptionTypeHandler exceptionTypeHandler, Throwable e){
        return ApiException.builder()
                .code(exceptionTypeHandler.code)
                .description(exceptionTypeHandler.description)
                .statusCode(exceptionTypeHandler.status)
                .apiExceptionDetails(Arrays.asList(
                        ApiExceptionDetail.builder()
                                .code(e.toString())
                                .component(appName)
                                .message(e.getMessage())
                                .build()))
                .build();
    }

}
