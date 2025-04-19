package com.ramees.core_banking_service.controller;

import com.ramees.core_banking_service.request.FundTransferRequest;
import com.ramees.core_banking_service.request.UtilityPaymentRequest;
import com.ramees.core_banking_service.response.FundTransferResponse;
import com.ramees.core_banking_service.response.UtilityPaymentResponse;
import com.ramees.core_banking_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/transfer")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/fund-transfer")
    public ResponseEntity<FundTransferResponse> fundTrasferBetweenAccounts(@RequestBody FundTransferRequest fundTransferRequest){
        return new ResponseEntity<>(transactionService.fundTransferBetweenAccounts(fundTransferRequest), HttpStatus.OK);
    }

    @PostMapping("/utility-payment")
    public ResponseEntity<UtilityPaymentResponse> utilityPayment(@RequestBody UtilityPaymentRequest utilityPaymentRequest){
        return new ResponseEntity<>(transactionService.utilityPayment(utilityPaymentRequest),HttpStatus.OK);
    }
}
