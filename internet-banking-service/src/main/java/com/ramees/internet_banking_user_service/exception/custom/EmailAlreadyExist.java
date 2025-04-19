package com.ramees.internet_banking_user_service.exception.custom;

public class EmailAlreadyExist extends RuntimeException{

      public EmailAlreadyExist(String message){
        super(message);
    }
}
