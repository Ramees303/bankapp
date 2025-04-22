package com.ramees.internet_banking_user_service.internal.controller;


import com.ramees.internet_banking_user_service.internal.response.UserResponse;
import com.ramees.internet_banking_user_service.request.FundTransferRequest;
import com.ramees.internet_banking_user_service.request.UtilityPaymentRequest;
import com.ramees.internet_banking_user_service.response.FundTransferResponse;
import com.ramees.internet_banking_user_service.response.UtilityPaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "core-banking-service")
public interface BankingCoreRestClient {

    @GetMapping(path = "/api/v1/user/{identification}")
    public UserResponse getUserByIdentification(@PathVariable("identification") String identificationNumber);


    @PostMapping("/fund-transfer")
    public FundTransferResponse fundTrasferBetweenAccounts(@RequestBody FundTransferRequest fundTransferRequest);


    @PostMapping("/utility-payment")
    public UtilityPaymentResponse utilityPayment(@RequestBody UtilityPaymentRequest utilityPaymentRequest);
}
