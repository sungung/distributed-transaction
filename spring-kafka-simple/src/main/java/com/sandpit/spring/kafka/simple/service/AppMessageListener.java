package com.sandpit.spring.kafka.simple.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.retry.annotation.Backoff;

import com.sandpit.spring.kafka.simple.dto.OrderDto;
import com.sandpit.spring.kafka.simple.dto.StockDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppMessageListener {
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	@KafkaListener(id="order", topics="order")
	public void batchListener(List<OrderDto> orders, @Header(KafkaHeaders.OFFSET)long offset) {
		log.info(">>> rec order: offset: {}", offset);
		if (orders.size() == 1) throw new RuntimeException("Can't accept single order. Sorry!");
		List<StockDto> stocks = orders.stream().map(o -> 
			StockDto.builder().item(o.getItem()).build()).collect(Collectors.toList());
		stocks.forEach(s -> template.send("stock", s));
		try {
			Thread.sleep(1000);
			log.info("Hit enter to proceed order");
			System.in.read();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@KafkaListener(id="stock", topics="stock")
	public void stockListener(List<StockDto> stocks,  
			@Header(KafkaHeaders.OFFSET) long offset) {
		log.info(">>> rec stock: offset: {}, stock: {}", offset, stocks);
	}
	
	@KafkaListener(id = "dlq", topicPattern = ".*\\.DLT$")
	public void dltListen(byte[] in) {
		log.info("DLT: " + new String(in));
	}
	
}
