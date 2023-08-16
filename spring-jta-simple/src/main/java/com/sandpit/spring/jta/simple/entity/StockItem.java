package com.sandpit.spring.jta.simple.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
public class StockItem {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String item;
    
    @Column(name = "available_qty", nullable = false)
    private int availableQuantity;

	public StockItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockItem(String item, int availableQuantity) {
		super();
		this.item = item;
		this.availableQuantity = availableQuantity;
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

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
    
    
}
