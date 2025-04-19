package com.ramees.internet_banking_user_service.dto;

import com.ramees.internet_banking_user_service.enums.TransactionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundTransfer {

    private Long id;
    private String transactionReference;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private TransactionStatus status;
}
