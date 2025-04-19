package com.ramees.internet_banking_user_service.internal.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class BankAccount {

    private Long id;
    private String number;
    private String type;
    private String status;
    private BigDecimal availableBalance;
    private BigDecimal actualBalance;

}
