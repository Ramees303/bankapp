package com.ramees.core_banking_service.service;

import com.ramees.core_banking_service.entity.BankAccountEntity;
import com.ramees.core_banking_service.entity.TransactionEntity;
import com.ramees.core_banking_service.entity.UtilityAccountEntity;
import com.ramees.core_banking_service.enums.TransactionType;
import com.ramees.core_banking_service.exception.custom.BankAccountNotFound;
import com.ramees.core_banking_service.exception.custom.InsufficientBalanceException;
import com.ramees.core_banking_service.exception.custom.UtilityAccountNotFound;
import com.ramees.core_banking_service.repository.BankAccountRepository;
import com.ramees.core_banking_service.repository.TransactionRepository;
import com.ramees.core_banking_service.repository.UtilityAccountRepository;
import com.ramees.core_banking_service.request.FundTransferRequest;
import com.ramees.core_banking_service.request.UtilityPaymentRequest;
import com.ramees.core_banking_service.response.FundTransferResponse;
import com.ramees.core_banking_service.response.UtilityPaymentResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {

    private  final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;
    private final UtilityAccountRepository utilityAccountRepository;


    public FundTransferResponse fundTransferBetweenAccounts(FundTransferRequest fundTransferRequest) {

        BankAccountEntity fromBankAccountEntity=bankAccountRepository.findByNumberForUpdate(fundTransferRequest.getFromAccount()).
                orElseThrow(()->new BankAccountNotFound("Bank account not found with account number: "+fundTransferRequest.getFromAccount()));
        BankAccountEntity toBankAccountEntity=bankAccountRepository.findByNumberForUpdate(fundTransferRequest.getToAccount()).
                orElseThrow(()->new BankAccountNotFound("Bank account not found with account number:"+fundTransferRequest.getToAccount()));

        validateAccount(fromBankAccountEntity.getActualBalance(),fundTransferRequest.getAmount());
        String transactionId=internalFundTransfer(fromBankAccountEntity,toBankAccountEntity,fundTransferRequest.getAmount());
        return  FundTransferResponse.builder()
                .transactionId(transactionId)
                .message("Transaction successfully completed").build();
    }


    private void validateAccount(BigDecimal actualBalance, BigDecimal amount) {
        if(actualBalance.compareTo(BigDecimal.ZERO)<0 || actualBalance.compareTo(amount) < 0){
            throw new InsufficientBalanceException("Insufficient funds for this transaction");
        }
    }

    private String internalFundTransfer(BankAccountEntity fromBankAccountEntity, BankAccountEntity toBankAccountEntity, BigDecimal amount) {
        String transactionId= UUID.randomUUID().toString();
        fromBankAccountEntity.setActualBalance(fromBankAccountEntity.getActualBalance().subtract(amount));
        fromBankAccountEntity.setAvailableBalance(fromBankAccountEntity.getAvailableBalance().subtract(amount));
        bankAccountRepository.save(fromBankAccountEntity);

        transactionRepository.save(TransactionEntity.builder()
                        .transactionId(transactionId)
                        .amount(amount.negate())
                        .referenceNumber(toBankAccountEntity.getNumber())
                        .transactionType(TransactionType.FUND_TRANSFER)
                        .bankAccount(fromBankAccountEntity)
                        .build());

        toBankAccountEntity.setAvailableBalance(toBankAccountEntity.getAvailableBalance().add(amount));
        toBankAccountEntity.setActualBalance(toBankAccountEntity.getActualBalance().add(amount));
        bankAccountRepository.save(toBankAccountEntity);

        transactionRepository.save(TransactionEntity.builder()
                        .transactionId(transactionId)
                        .transactionType(TransactionType.FUND_TRANSFER)
                        .amount(amount)
                        .bankAccount(toBankAccountEntity)
                        .referenceNumber(fromBankAccountEntity.getNumber())
                        .build());

        return  transactionId;

    }

    public UtilityPaymentResponse utilityPayment(UtilityPaymentRequest utilityPaymentRequest) {

        String transactionId=UUID.randomUUID().toString();

        BankAccountEntity userBankAccount=bankAccountRepository.findByNumberForUpdate(utilityPaymentRequest.getAccountNumber()).orElseThrow(()->new BankAccountNotFound("Bank account not found with account number: "+utilityPaymentRequest.getAccountNumber()));
        validateAccount(userBankAccount.getActualBalance(),utilityPaymentRequest.getAmount());
        UtilityAccountEntity utilityAccount =utilityAccountRepository.findById(utilityPaymentRequest.getProviderId()).orElseThrow(()->new UtilityAccountNotFound("utility account not found with provider id :"+utilityPaymentRequest.getProviderId()));

        // do at last.....third party utility serice integration

        userBankAccount.setAvailableBalance(userBankAccount.getAvailableBalance().subtract(utilityPaymentRequest.getAmount()));
        userBankAccount.setActualBalance(userBankAccount.getActualBalance().subtract(utilityPaymentRequest.getAmount()));
        bankAccountRepository.save(userBankAccount);

        transactionRepository.save(TransactionEntity.builder()
                        .transactionType(TransactionType.UTILITY_PAYMENT)
                        .referenceNumber(utilityPaymentRequest.getReferenceNumber())
                        .transactionId(transactionId)
                        .bankAccount(userBankAccount)
                        .amount(utilityPaymentRequest.getAmount().negate()).build());

        return  UtilityPaymentResponse.builder()
                        .transactionId(transactionId)
                                .message("Utility payment successfully completed").build();
    }
}
