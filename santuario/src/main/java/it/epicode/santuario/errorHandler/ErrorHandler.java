package it.epicode.santuario.errorHandler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice// gestisce eccezioni tramite spring e verifica la presenza dei metodi che gestiscono gli errori
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException error) {
        String strError = error.getMessage();
        return new ResponseEntity<>(strError, HttpStatus.NOT_FOUND);


    }
}