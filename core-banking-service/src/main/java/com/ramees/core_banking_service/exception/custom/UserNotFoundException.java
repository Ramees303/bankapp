package com.ramees.core_banking_service.exception.custom;


public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }

}
