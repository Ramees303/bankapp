package com.ramees.core_banking_service.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class FundTransferResponse {
    String transactionId;
    String message;
}
