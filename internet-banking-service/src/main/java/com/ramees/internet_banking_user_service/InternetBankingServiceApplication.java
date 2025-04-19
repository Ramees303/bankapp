package com.ramees.internet_banking_user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class InternetBankingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternetBankingServiceApplication.class, args);
	}

}
