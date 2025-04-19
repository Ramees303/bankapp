package com.ramees.internet_banking_user_service.entity;

import com.ramees.internet_banking_user_service.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "fund_transfer")
public class FundTransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_reference")
    private String transactionReference;

    @Column(name="from_account")
    private String fromAccount;

    @Column(name = "to_account")
    private String toAccount;

    private BigDecimal amount;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;
}
