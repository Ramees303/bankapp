package com.ramees.internet_banking_user_service.service;

import com.ramees.internet_banking_user_service.dto.FundTransfer;
import com.ramees.internet_banking_user_service.entity.FundTransferEntity;
import com.ramees.internet_banking_user_service.enums.TransactionStatus;
import com.ramees.internet_banking_user_service.mapper.FundTransferMapper;
import com.ramees.internet_banking_user_service.repository.FundTransferRepository;
import com.ramees.internet_banking_user_service.request.FundTransferRequest;
import com.ramees.internet_banking_user_service.response.FundTransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FundTransferService {

    private final FundTransferRepository fundTransferRepository;
    private  final FundTransferMapper fundTransferMapper;

    public FundTransferResponse transferFund(FundTransferRequest fundTransferRequest) {
        String transactionId= UUID.randomUUID().toString();
        FundTransferEntity entity= new FundTransferEntity();
        BeanUtils.copyProperties(fundTransferRequest,entity);
        entity.setStatus(TransactionStatus.PENDING);
        FundTransferEntity savedfundTransferEntity=fundTransferRepository.save(entity);
        return FundTransferResponse.builder().
                message("fund transfer successfull").
                transactionId(transactionId)
                .build();
    }

    public List<FundTransfer> getAlltransferFund(Pageable pageable) {
        return fundTransferMapper.convertToDtoList(fundTransferRepository.findAll(pageable).getContent());
    }
}
