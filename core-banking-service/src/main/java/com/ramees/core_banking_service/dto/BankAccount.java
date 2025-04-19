package com.ramees.core_banking_service.dto;

import com.ramees.core_banking_service.entity.TransactionEntity;
import com.ramees.core_banking_service.entity.UserEntity;
import com.ramees.core_banking_service.enums.AccountStatus;
import com.ramees.core_banking_service.enums.AccountType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BankAccount {

    private Long id;
    private String number;
    private AccountType type;
    private AccountStatus status;
    private BigDecimal availableBalance;
    private BigDecimal actualBalance;
    private UserEntity user;
    private List<Transaction> transaction;
}
