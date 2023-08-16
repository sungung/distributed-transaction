package com.sandpit.spring.jta.simple.repository;

import org.springframework.data.repository.CrudRepository;

import com.sandpit.spring.jta.simple.entity.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

}
