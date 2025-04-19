package com.ramees.internet_banking_user_service.request;

import com.ramees.internet_banking_user_service.enums.UserStatus;
import lombok.Data;

@Data
public class UserUpdateRequest {

    private UserStatus userStatus;
}
