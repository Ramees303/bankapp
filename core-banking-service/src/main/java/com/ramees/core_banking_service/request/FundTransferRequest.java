package com.ramees.core_banking_service.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundTransferRequest {

    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
}
