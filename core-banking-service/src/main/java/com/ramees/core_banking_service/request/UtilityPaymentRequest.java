package com.ramees.core_banking_service.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UtilityPaymentRequest {
    private  Long providerId;
    private BigDecimal amount;
    private String accountNumber;
    private String referenceNumber;
}
