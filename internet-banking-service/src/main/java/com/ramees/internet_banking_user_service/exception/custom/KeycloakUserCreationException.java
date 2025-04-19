package com.ramees.internet_banking_user_service.exception.custom;

public class KeycloakUserCreationException extends RuntimeException{

    public KeycloakUserCreationException(String message){
        super(message);
    }

}
