package com.sandpit.spring.jta.simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sandpit.spring.jta.simple.dto.OrderDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderConsumer {

	@Autowired
	private StockService stockService;

	@JmsListener(destination = OrderService.Q_ORDER_SIMPLE_SUCCESS)
	public void receiveOrderAndSuccess(OrderDto order) {
		log.info("Received >>> Success");
	}
	
	@Transactional
	@JmsListener(destination = OrderService.Q_ORDER_SIMPLE_FAIL)
	public void receiveOrderAndFail(OrderDto message)  {
		log.info("Received >>> {}",  message.toString());
		if (message.isHasFault()) {
			throw new OrderException("Unable tohandle");
		} 
	}
	
	@JmsListener(destination = OrderService.Q_RETRY)
	public void retry(String message)  {
		log.info("Received >>> Failed");
		throw new OrderException("Unable tohandle");
	}
	
}
