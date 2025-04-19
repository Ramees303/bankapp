package com.ramees.internet_banking_user_service.entity;

import com.ramees.internet_banking_user_service.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "utility_payment")
public class UtilityPaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider_id")
    private Long providerId;

    @Column(name="transaction_id")
    private String transactionId;

    @Column(name = "reference_number")
    private String referenceNumber;

    private BigDecimal amount;

    private  String account;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

}
