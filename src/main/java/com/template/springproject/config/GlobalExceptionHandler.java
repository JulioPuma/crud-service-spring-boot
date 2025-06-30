package com.template.springproject.config;

import com.template.springproject.util.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

/**
 * GlobalExceptionHandler Class <br/>
 * Manejador de excepciones.
 * @author JulioPumahuacre
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  handleNoSuchElementException method. <br/>
     *  Si durante la ejecución se dispara la exception NoSuchElementException
     *  se captura y se convierte en un objeto personalizado.
     * @param ex NoSuchElementException
     * @return ResponseEntity
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiException> handleNoSuchElementException(NoSuchElementException ex) {
        ApiException apiException = new ApiException("ERROR", ex.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}
