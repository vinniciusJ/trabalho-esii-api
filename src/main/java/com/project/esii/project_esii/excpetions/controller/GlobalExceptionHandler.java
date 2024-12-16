package com.project.esii.project_esii.excpetions.controller;

import com.project.esii.project_esii.excpetions.config.ErrorDescription;
import com.project.esii.project_esii.excpetions.type.EntityNotFoundExcpetion;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDescription> handleEntityNotFoundException(EntityNotFoundExcpetion ex) {
        String message = ex.getEntity() + " não encontrado(a) para " + ex.getField() + " " + ex.getValue();
        ErrorDescription errorResponse = new ErrorDescription(
                HttpStatus.NOT_FOUND.value(),
                message
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDescription> handleDataIntegrityViolationException() {
        ErrorDescription errorDescription = new ErrorDescription(
                HttpStatus.CONFLICT.value(),
                "Dados duplicados: um ou mais campos únicos já estão em uso."
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDescription);
    }

}