package com.sandpit.spring.jta.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class DistributedTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedTransactionsApplication.class, args);
	}
}
