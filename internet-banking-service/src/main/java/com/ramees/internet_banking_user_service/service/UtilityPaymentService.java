package com.ramees.internet_banking_user_service.service;

import com.ramees.internet_banking_user_service.dto.UtilityPayment;
import com.ramees.internet_banking_user_service.entity.UtilityPaymentEntity;
import com.ramees.internet_banking_user_service.enums.TransactionStatus;
import com.ramees.internet_banking_user_service.internal.controller.BankingCoreRestClient;
import com.ramees.internet_banking_user_service.mapper.UtilityPaymentMapper;
import com.ramees.internet_banking_user_service.repository.UtilityPaymentRepository;
import com.ramees.internet_banking_user_service.request.UtilityPaymentRequest;
import com.ramees.internet_banking_user_service.response.UtilityPaymentResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class UtilityPaymentService {

    private  final UtilityPaymentRepository utilityPaymentRepository;

    private  final UtilityPaymentMapper utilityPaymentMapper;

    private final BankingCoreRestClient bankingCoreRestClient;


    public UtilityPaymentResponse utilityPayment(UtilityPaymentRequest utilityPaymentRequest) {

        UtilityPaymentEntity utilityPaymentEntity=new UtilityPaymentEntity();
        BeanUtils.copyProperties(utilityPaymentRequest,utilityPaymentEntity);

        utilityPaymentEntity.setStatus(TransactionStatus.PROCESSING);
        UtilityPaymentEntity utilityPayment=utilityPaymentRepository.save(utilityPaymentEntity);
        UtilityPaymentResponse utilityPaymentResponse=bankingCoreRestClient.utilityPayment(utilityPaymentRequest);


        utilityPayment.setTransactionId(utilityPaymentResponse.getTransactionId());
        utilityPayment.setStatus(TransactionStatus.SUCCESS);

        utilityPaymentRepository.save(utilityPayment);

        return UtilityPaymentResponse.builder()
                .message(utilityPaymentResponse.getMessage())
                .transactionId(utilityPayment.getTransactionId())
                .build();

    }

    public List<UtilityPayment> getAllUtilityPayment(Pageable pageable) {
        return  utilityPaymentMapper.convertToDtoList( utilityPaymentRepository.findAll(pageable).getContent());
    }
}
