package com.sandpit.spring.jta.simple.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String item;
    
    @Column
    private int quantity;
    
	public OrderItem(String item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}

	public OrderItem() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
