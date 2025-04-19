package com.ramees.internet_banking_user_service.exception.custom;


public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }

}
