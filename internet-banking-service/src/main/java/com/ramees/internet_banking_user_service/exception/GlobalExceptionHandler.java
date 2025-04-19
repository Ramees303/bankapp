package com.ramees.internet_banking_user_service.exception;


import com.ramees.internet_banking_user_service.exception.custom.EmailAlreadyExist;
import com.ramees.internet_banking_user_service.exception.custom.EmailNotFound;
import com.ramees.internet_banking_user_service.exception.custom.KeycloakUserCreationException;
import com.ramees.internet_banking_user_service.exception.custom.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundExceptionHandler(UserNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(EmailAlreadyExist.class)
    public ResponseEntity<String> emailAlreadyExistsExceptionHandler(EmailAlreadyExist exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(KeycloakUserCreationException.class)
    public ResponseEntity<String> keycloakUserCreationExceptionHandler(KeycloakUserCreationException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailNotFound.class)
    public ResponseEntity<String> emailNotFoundExceptionHandler(EmailNotFound exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }




}
