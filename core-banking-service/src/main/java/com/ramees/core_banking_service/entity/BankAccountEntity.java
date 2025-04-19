package com.ramees.core_banking_service.entity;

import com.ramees.core_banking_service.enums.AccountStatus;
import com.ramees.core_banking_service.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="banking_core_account")
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "available_balance")
    private BigDecimal availableBalance;

    @Column(name = "actual_balance")
    private BigDecimal actualBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<TransactionEntity> transaction;

}
