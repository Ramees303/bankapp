package com.ramees.core_banking_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {

    private Long id;
    private BigDecimal amount;
    private BankAccount bankAccount;
    private String referenceNumber;

}
