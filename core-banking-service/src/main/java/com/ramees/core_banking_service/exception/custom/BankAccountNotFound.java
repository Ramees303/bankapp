package com.ramees.core_banking_service.exception.custom;

public class BankAccountNotFound extends RuntimeException{
   public BankAccountNotFound(String message){
        super(message);
    }
}
