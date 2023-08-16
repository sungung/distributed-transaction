package com.sandpit.spring.jta.simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandpit.spring.jta.simple.dto.OrderDto;
import com.sandpit.spring.jta.simple.entity.OrderItem;
import com.sandpit.spring.jta.simple.repository.OrderItemRepository;
import com.sandpit.spring.jta.simple.repository.StockItemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	
	public static final String Q_ORDER_SIMPLE_SUCCESS = "simple.success";
	public static final String Q_ORDER_SIMPLE_FAIL = "simple.fail";
	
	public static final String Q_RETRY ="q.retry";
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private OrderItemRepository orderRepository;
	
	@Autowired
	private StockItemRepository stockRepository;
	
	public OrderService(JmsTemplate jmsTemplate) {
		super();
		jmsTemplate.setSessionTransacted(true);
		this.jmsTemplate = jmsTemplate;
	}

	public long getOrderCount() {
		return orderRepository.count();
	}

	@Transactional
	public void transactions(OrderDto orderDto) {
		OrderItem order = new OrderItem();
		order.setItem(orderDto.getItem());
		order.setQuantity(orderDto.getQuantity());
		
		order = orderRepository.save(order);
		
		if (order != null) {
			orderDto = OrderDto.builder().item(order.getItem()).quantity(order.getQuantity()).build();
			jmsTemplate.convertAndSend(Q_ORDER_SIMPLE_SUCCESS, orderDto);
		}		
	}	
	
	@Transactional
	public void rollback(OrderDto orderDto) {
		OrderItem order = new OrderItem();
		order.setItem(orderDto.getItem());
		order.setQuantity(orderDto.getQuantity());
		
		order = orderRepository.save(order);
		
		if (order != null) {
			orderDto = OrderDto.builder().item(order.getItem()).quantity(order.getQuantity()).build();
			jmsTemplate.convertAndSend(Q_ORDER_SIMPLE_SUCCESS, orderDto);
			throw new RuntimeException("BOOM!");
		}
	}
	
	@Transactional
	public void consumerError(OrderDto orderDto) {
		OrderItem order = new OrderItem();
		order.setItem(orderDto.getItem());
		order.setQuantity(orderDto.getQuantity());
		
		order = orderRepository.save(order);
		
		if (order != null) {
			orderDto = OrderDto.builder().item(order.getItem()).quantity(order.getQuantity()).hasFault(false).build();
			log.info("Producing healthy message.");
			jmsTemplate.convertAndSend(Q_ORDER_SIMPLE_FAIL, orderDto);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			orderDto = OrderDto.builder().item(order.getItem()).quantity(order.getQuantity()).hasFault(true).build();
			log.info("Producing faulty message.");
			jmsTemplate.convertAndSend(Q_ORDER_SIMPLE_FAIL, orderDto);			
		}		
	}	

}
