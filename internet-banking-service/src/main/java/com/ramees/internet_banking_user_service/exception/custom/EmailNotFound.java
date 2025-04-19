package com.ramees.internet_banking_user_service.exception.custom;

public class EmailNotFound extends RuntimeException{
    public EmailNotFound(String message){
        super(message);
    }

}
