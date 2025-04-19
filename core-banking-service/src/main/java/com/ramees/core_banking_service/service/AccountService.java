package com.ramees.core_banking_service.service;

import com.ramees.core_banking_service.dto.BankAccount;
import com.ramees.core_banking_service.dto.UtilityAccount;
import com.ramees.core_banking_service.entity.BankAccountEntity;
import com.ramees.core_banking_service.entity.UtilityAccountEntity;
import com.ramees.core_banking_service.exception.custom.BankAccountNotFound;
import com.ramees.core_banking_service.exception.custom.UtilityAccountNotFound;
import com.ramees.core_banking_service.mapper.BankAccountMapper;
import com.ramees.core_banking_service.mapper.UtilityAccountMapper;
import com.ramees.core_banking_service.repository.BankAccountRepository;
import com.ramees.core_banking_service.repository.UtilityAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UtilityAccountRepository utilityAccountRepository;
    private final BankAccountMapper bankAccountMapper;
    private final UtilityAccountMapper utilityAccountMapper;

    public BankAccount getBankAccountUsingAccountNumber(String accountNumber) {
       BankAccountEntity bankAccountEntity=bankAccountRepository.findByNumber(accountNumber).orElseThrow(()->new BankAccountNotFound("Bank account not found with account number:"+accountNumber));
       return bankAccountMapper.convertToDto(bankAccountEntity);
    }

    public UtilityAccount getUtilityAccountUsingProviderName(String providerName) {
        UtilityAccountEntity utilityAccountEntity =utilityAccountRepository.findByProviderName(providerName).orElseThrow(()->new UtilityAccountNotFound("utility account not found with provider name:"+providerName));
        return utilityAccountMapper.convertToDto(utilityAccountEntity);
    }
}
