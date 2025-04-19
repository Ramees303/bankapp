package com.ramees.internet_banking_user_service.service;

import com.ramees.internet_banking_user_service.dto.UtilityPayment;
import com.ramees.internet_banking_user_service.entity.UtilityPaymentEntity;
import com.ramees.internet_banking_user_service.enums.TransactionStatus;
import com.ramees.internet_banking_user_service.mapper.UtilityPaymentMapper;
import com.ramees.internet_banking_user_service.repository.UtilityPaymentRepository;
import com.ramees.internet_banking_user_service.request.UtilityPaymentRequest;
import com.ramees.internet_banking_user_service.response.FundTransferResponse;
import com.ramees.internet_banking_user_service.response.UtilityPaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UtilityPaymentService {

    private  final UtilityPaymentRepository utilityPaymentRepository;

    private  final UtilityPaymentMapper utilityPaymentMapper;


    public UtilityPaymentResponse utilityPayment(UtilityPaymentRequest utilityPaymentRequest) {

        UtilityPaymentEntity utilityPaymentEntity=new UtilityPaymentEntity();
        BeanUtils.copyProperties(utilityPaymentRequest,utilityPaymentEntity);
        utilityPaymentEntity.setStatus(TransactionStatus.PROCESSING);
        UtilityPaymentEntity utilityPayment=utilityPaymentRepository.save(utilityPaymentEntity);

        String transactionId= UUID.randomUUID().toString();
        utilityPayment.setTransactionId(transactionId);
        utilityPayment.setStatus(TransactionStatus.SUCCESS);

        utilityPaymentRepository.save(utilityPayment);

        return UtilityPaymentResponse.builder()
                .message("utility payment success")
                .transactionId(transactionId)
                .build();

    }

    public List<UtilityPayment> getAllUtilityPayment(Pageable pageable) {
        return  utilityPaymentMapper.convertToDtoList( utilityPaymentRepository.findAll(pageable).getContent());
    }
}
