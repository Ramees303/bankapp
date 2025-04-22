package com.ramees.internet_banking_user_service.service;

import com.ramees.internet_banking_user_service.dto.FundTransfer;
import com.ramees.internet_banking_user_service.entity.FundTransferEntity;
import com.ramees.internet_banking_user_service.enums.TransactionStatus;
import com.ramees.internet_banking_user_service.internal.controller.BankingCoreRestClient;
import com.ramees.internet_banking_user_service.mapper.FundTransferMapper;
import com.ramees.internet_banking_user_service.repository.FundTransferRepository;
import com.ramees.internet_banking_user_service.request.FundTransferRequest;
import com.ramees.internet_banking_user_service.response.FundTransferResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FundTransferService {

    private final FundTransferRepository fundTransferRepository;
    private  final FundTransferMapper fundTransferMapper;
    private  final BankingCoreRestClient bankingCoreRestClient;

    public FundTransferResponse transferFund(FundTransferRequest fundTransferRequest) {
        FundTransferEntity entity= new FundTransferEntity();
        BeanUtils.copyProperties(fundTransferRequest,entity);

        FundTransferResponse fundTransferResponse=bankingCoreRestClient.fundTrasferBetweenAccounts(fundTransferRequest);
        entity.setStatus(TransactionStatus.SUCCESS);

        FundTransferEntity savedfundTransferEntity=fundTransferRepository.save(entity);
        return FundTransferResponse.builder().
                message(fundTransferResponse.getMessage()).
                transactionId(fundTransferResponse.getTransactionId())
                .build();
    }

    public List<FundTransfer> getAlltransferFund(Pageable pageable) {
        return fundTransferMapper.convertToDtoList(fundTransferRepository.findAll(pageable).getContent());
    }
}
