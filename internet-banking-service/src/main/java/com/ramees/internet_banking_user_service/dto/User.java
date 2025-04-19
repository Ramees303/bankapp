package com.ramees.internet_banking_user_service.dto;

import com.ramees.internet_banking_user_service.enums.UserStatus;
import lombok.Data;

@Data
public class User {

    private Long id;
    private String authId;
    private String identification;
    private  String email;
    private UserStatus status;
    private String password;
}
