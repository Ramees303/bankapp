package com.ramees.core_banking_service.entity;

import com.ramees.core_banking_service.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@Entity
@Table(name = "banking_core_transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String referenceNumber;

    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccountEntity bankAccount;
}
