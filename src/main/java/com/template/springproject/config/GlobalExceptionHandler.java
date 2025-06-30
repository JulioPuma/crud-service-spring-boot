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
   * handleException method. <br>
   * Si la excepcion producida es de tipo ApiException, entonces se propaga.
   * Caso contrario se retorna la excepcion original.
   *
   * @param ex Exception
   * @return ResponseEntity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Exception> handleException(Exception ex) {
    if (ex instanceof ApiException) {
      return new ResponseEntity<>(ex, ((ApiException) ex).getStatusCode());
    }
    return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
