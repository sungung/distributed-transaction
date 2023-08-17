package com.sandpit.spring.kafka.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SimpleKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleKafkaApplication.class, args);
	}

}
