package com.sandpit.spring.jta.simple.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandpit.spring.jta.simple.dto.OrderDto;
import com.sandpit.spring.jta.simple.entity.StockItem;
import com.sandpit.spring.jta.simple.repository.StockItemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockService {
	
	@Autowired
	private StockItemRepository stockItemRepostiory;
	
	public void process(OrderDto order) throws OrderException {
		Optional<StockItem> opt = stockItemRepostiory.findByItem(order.getItem());
		if (opt.isPresent()) {
			StockItem stock = opt.get();
			if (stock.getAvailableQuantity() <= 0) {
				throw new OrderException("Runout stock");
			} else {
				stock.setAvailableQuantity(stock.getAvailableQuantity() - order.getQuantity());
				stockItemRepostiory.save(stock);
			}
		} else {
			// initialise default stock
			stockItemRepostiory.save(new StockItem(order.getItem(), order.getQuantity()));
		}
	}
}
