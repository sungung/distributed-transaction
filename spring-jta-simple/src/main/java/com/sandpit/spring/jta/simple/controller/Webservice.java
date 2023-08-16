package com.sandpit.spring.jta.simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandpit.spring.jta.simple.dto.OrderDto;
import com.sandpit.spring.jta.simple.service.OrderService;

@RestController
@RequestMapping("/api")
public class Webservice {
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/order/transactions")
	public ResponseEntity<Long> transactions(@RequestBody OrderDto order) {
		orderService.transactions(order);		
		return ResponseEntity.ok(orderService.getOrderCount());
	}
	
	@PostMapping("/order/transactions/rollback")
	public ResponseEntity<Long> rollback(@RequestBody OrderDto order) {
		orderService.rollback(order);		
		return ResponseEntity.ok(orderService.getOrderCount());
	}
	
	@PostMapping("/order/transactions/consumer/exception")
	public ResponseEntity<Long> consumerException(@RequestBody OrderDto order) {
		orderService.consumerError(order);		
		return ResponseEntity.ok(orderService.getOrderCount());
	}
	
	
}
