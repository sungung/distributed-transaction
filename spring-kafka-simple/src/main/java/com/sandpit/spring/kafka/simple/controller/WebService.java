package com.sandpit.spring.kafka.simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sandpit.spring.kafka.simple.dto.OrderDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WebService {

	@Autowired
	private KafkaTemplate<String, Object> template;

	@PostMapping(path = "/orders")
	public void orders(@RequestBody List<OrderDto> orders) {
		this.template.executeInTransaction(t -> {
			orders.stream().forEach(order -> t.send("order", order));
			return null;
		});
	}
}
