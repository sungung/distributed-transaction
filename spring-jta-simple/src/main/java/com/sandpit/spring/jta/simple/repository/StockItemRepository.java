package com.sandpit.spring.jta.simple.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sandpit.spring.jta.simple.entity.StockItem;

public interface StockItemRepository extends CrudRepository<StockItem, Long> {

	Optional<StockItem> findByItem(String item);

}
