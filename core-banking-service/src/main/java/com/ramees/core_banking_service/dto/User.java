package com.ramees.core_banking_service.dto;

import com.ramees.core_banking_service.entity.BankAccountEntity;
import lombok.Data;

import java.util.List;

@Data
public class User {


    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String identificationNumber;
    private List<BankAccount> bankAccount;


}
