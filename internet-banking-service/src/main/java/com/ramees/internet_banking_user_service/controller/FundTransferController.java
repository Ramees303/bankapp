package com.ramees.internet_banking_user_service.controller;

import com.ramees.internet_banking_user_service.dto.FundTransfer;
import com.ramees.internet_banking_user_service.request.FundTransferRequest;
import com.ramees.internet_banking_user_service.response.FundTransferResponse;
import com.ramees.internet_banking_user_service.service.FundTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/transfer")
public class FundTransferController {

    private final FundTransferService fundTransferService;

    @PostMapping
    public ResponseEntity<FundTransferResponse> transferFund(@RequestBody FundTransferRequest fundTransferRequest){
       return new ResponseEntity<>(fundTransferService.transferFund(fundTransferRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FundTransfer>> getAlltransferFund(Pageable pageable){
        return new ResponseEntity<>(fundTransferService.getAlltransferFund(pageable),HttpStatus.OK);
    }

}
