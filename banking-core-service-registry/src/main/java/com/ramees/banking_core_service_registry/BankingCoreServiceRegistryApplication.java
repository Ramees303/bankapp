package com.ramees.banking_core_service_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BankingCoreServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingCoreServiceRegistryApplication.class, args);
	}

}
