package com.project.esii.project_esii.exceptions.controller;

import com.project.esii.project_esii.exceptions.config.ErrorDescription;
import com.project.esii.project_esii.exceptions.domain.*;
import com.project.esii.project_esii.excpetions.type.NotAllowedToUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundExcpetion.class)
    public ResponseEntity<ErrorDescription> handleEntityNotFoundException(EntityNotFoundExcpetion ex) {
        String message = ex.getEntity() + " não encontrado(a) para " + ex.getField() + " " + ex.getValue();
        ErrorDescription errorResponse = new ErrorDescription(
                HttpStatus.NOT_FOUND.value(),
                message
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(RegistrationEmailException.class)
    public ResponseEntity<ErrorDescription> handleRegistrationEmailException(RegistrationEmailException ex) {
        String message = "Não foi possível enviar o email de verificação, o cadastro não foi concluído";
        ErrorDescription errorResponse = new ErrorDescription(
                HttpStatus.NOT_FOUND.value(),
                message
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(EmailNotVerifiedException.class)
    public ResponseEntity<ErrorDescription> handleEmailNotVerified(EmailNotVerifiedException ex) {
        ErrorDescription errorResponse = new ErrorDescription(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(EventActionNotValidForEventSubscriptionException.class)
    public ResponseEntity<ErrorDescription> handleEventActionNotValidForEventSubscriptionException(EventActionNotValidForEventSubscriptionException ex) {
        String message = "Não existe inscrição para esta ação";
        ErrorDescription errorResponse = new ErrorDescription(
                HttpStatus.NOT_FOUND.value(),
                message
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoVacancyForMainEventActionException.class)
    public ResponseEntity<ErrorDescription> handleNoVacancyForMainEventActionException(NoVacancyForMainEventActionException ex) {
        String message = "Não há vagas disponíveis para a ação do evento com id " + ex.getValue();
        ErrorDescription errorResponse = new ErrorDescription(
                HttpStatus.NOT_FOUND.value(),
                message
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ExistingEventSubscriptionException.class)
    public ResponseEntity<ErrorDescription> handleExistingEventSubscriptionException(ExistingEventSubscriptionException ex) {
        String message = "Inscrição já existente";
        ErrorDescription errorResponse = new ErrorDescription(
                HttpStatus.NOT_FOUND.value(),
                message
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(EventActionNotValidForEventException.class)
    public ResponseEntity<ErrorDescription> handleEventActionNotValidForEventException(EventActionNotValidForEventException ex) {
        String message = "O evento não possui a ação informada";
        ErrorDescription errorResponse = new ErrorDescription(
                HttpStatus.NOT_FOUND.value(),
                message
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NotAllowedToUpdateException.class)
    public ResponseEntity<ErrorDescription> handleNotAllowedToUpdateException(NotAllowedToUpdateException ex) {
        String message = "Usuário não possui permissão para atualizar entidade " + ex.getEntity() + ", com " + ex.getField() +
                " " + ex.getValue();
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

    @ExceptionHandler(ExistingRegistrationEmailException.class)
    public ResponseEntity<ErrorDescription> handleDataIntegrityViolationException(ExistingRegistrationEmailException ex) {
        ErrorDescription errorDescription = new ErrorDescription(
                HttpStatus.CONFLICT.value(),
                "Cadastro existente de " + ex.getUserType() + " com o email " + ex.getEmail()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDescription);
    }

}
