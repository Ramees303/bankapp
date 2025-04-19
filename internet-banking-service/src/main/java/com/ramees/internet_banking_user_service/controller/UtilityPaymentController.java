package com.ramees.internet_banking_user_service.controller;

import com.ramees.internet_banking_user_service.dto.UtilityPayment;
import com.ramees.internet_banking_user_service.request.UtilityPaymentRequest;
import com.ramees.internet_banking_user_service.response.UtilityPaymentResponse;
import com.ramees.internet_banking_user_service.service.UtilityPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/utility-payment")
public class UtilityPaymentController {

    private final UtilityPaymentService utilityPaymentService;

    @PostMapping
    public ResponseEntity<UtilityPaymentResponse> utilityPayment(@RequestBody UtilityPaymentRequest utilityPaymentRequest){
        return new ResponseEntity<>(utilityPaymentService.utilityPayment(utilityPaymentRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UtilityPayment>> getAllUtilityPayment(Pageable pageable){
        return new ResponseEntity<>(utilityPaymentService.getAllUtilityPayment(pageable),HttpStatus.OK);
    }


}
