package com.ramees.internet_banking_user_service.dto;

import com.ramees.internet_banking_user_service.enums.TransactionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UtilityPayment {

    private Long id;
    private Long providerId;
    private String transactionId;
    private String referenceNumber;
    private BigDecimal amount;
    private  String account;
    private TransactionStatus status;
}
