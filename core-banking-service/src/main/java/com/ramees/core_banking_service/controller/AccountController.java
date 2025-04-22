package com.ramees.core_banking_service.controller;
import com.ramees.core_banking_service.dto.BankAccount;
import com.ramees.core_banking_service.dto.UtilityAccount;
import com.ramees.core_banking_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(path = "/bank-account/{accountNumber}")
    public ResponseEntity<BankAccount> getBankAccountUsingAccountNumber(@PathVariable("accountNumber")String accountNumber){
        return new ResponseEntity<>(accountService.getBankAccountUsingAccountNumber(accountNumber), HttpStatus.OK);
    }

    @GetMapping(path="/utility-account/{providerName}")
    public ResponseEntity<UtilityAccount> getUtilityAccountUsingProviderName(@PathVariable("providerName") String providerName){
        return new ResponseEntity<>(accountService.getUtilityAccountUsingProviderName(providerName),HttpStatus.OK);
    }



}
