package com.ramees.core_banking_service.exception;

import com.ramees.core_banking_service.exception.custom.BankAccountNotFound;
import com.ramees.core_banking_service.exception.custom.InsufficientBalanceException;
import com.ramees.core_banking_service.exception.custom.UserNotFoundException;
import com.ramees.core_banking_service.exception.custom.UtilityAccountNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundWithIdentificationNumberExceptionHandler(UserNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BankAccountNotFound.class)
    public ResponseEntity<String> bankAccountNotFoundExceptionHandler(BankAccountNotFound exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UtilityAccountNotFound.class)
    public ResponseEntity<String> utilityAccountNotFoundExceptionHandler(UtilityAccountNotFound exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> insufficientBalanceExceptionHolder(InsufficientBalanceException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

}
