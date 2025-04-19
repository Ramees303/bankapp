package com.ramees.internet_banking_user_service.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UtilityPaymentResponse {
    private String message;
    private  String transactionId;
}
