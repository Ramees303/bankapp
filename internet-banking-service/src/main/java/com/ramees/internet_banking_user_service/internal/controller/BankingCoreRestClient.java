package com.ramees.internet_banking_user_service.internal.controller;


import com.ramees.internet_banking_user_service.internal.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "core-banking-service")
public interface BankingCoreRestClient {

    @GetMapping(path = "/api/v1/user/{identification}")
    public UserResponse getUserByIdentification(@PathVariable("identification") String identificationNumber);


}
