package com.ramees.core_banking_service.exception.custom;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String message){
        super(message);
    }

}
